import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

def javaConfigPackageFolder = "src/main/java/${request.packageName}/config".replace('.', '/')

def pomFile = projectPath.resolve("pom.xml").toFile()
def applicationYmlPath = projectPath.resolve("src/main/resources/application.yml").toFile()
def applicationLocalYmlPath = projectPath.resolve("src/main/resources/application-default.yml").toFile()
def applicationTestYmlPath = projectPath.resolve("src/test/resources/application-test.yml").toFile()
def dockerComposeYmlPath = projectPath.resolve("docker-compose.yml").toFile()
def zenwaveScriptsPath = projectPath.resolve("zenwave-scripts.zw").toFile()

/**
 * Files that support per-line feature tags: `... // [feature]` or `... <!-- [feature] -->`.
 * Lines tagged with a feature that was NOT selected are removed.
 */
def taggedLineFiles = [zenwaveScriptsPath, pomFile]

/**
 * Files, dependencies and configuration specific to each technology.
 * When a technology is not selected, its dependencies, classes and configuration are removed.
 *
 * Supported keys per feature:
 *   isHidden               -> not offered in the interactive prompt, only activated through `parents`
 *   parents                -> features that are forced to `true` when this one is selected
 *   dependencies           -> `groupId:artifactId` removed from pom.xml (works for <dependencyManagement> BOMs too)
 *   pomProperties          -> <property> lines removed from pom.xml
 *   classes                -> files removed from src/main/java/<package>/config
 *   yamlKeys               -> keys removed from application.yml / application-default.yml / application-test.yml
 *   dockerComposeKeys      -> keys removed from docker-compose.yml
 *   searchAndReplace       -> [file, search, replace, isRegex] applied when the feature IS selected
 *   searchAndReplaceIfMissing -> [file, search, replace, isRegex] applied when the feature is NOT selected
 *
 * Lines in `taggedLineFiles` ending in `// [feature]` or `<!-- [feature] -->` are dropped when the
 * feature is not selected, and keep the line with the tag stripped when it is.
 *
 * `a+b` entries apply only when both `a` and `b` are selected.
 */
def config = [
        "jpa": [
                "dependencies": [
                        "org.hibernate.orm:hibernate-ant",
                        "org.springframework.boot:spring-boot-starter-data-jpa",
                        "org.postgresql:postgresql",
                ],
                "classes": ["DatabaseConfiguration.java"],
                "yamlKeys": [
                        "spring.datasource", "spring.jpa", "spring.sql",
                        "DATASOURCE_URL", "DATASOURCE_USERNAME", "DATASOURCE_PASSWORD",
                        "logging.level.org.hibernate.SQL"
                ],
                "dockerComposeKeys": ["services.postgresql"]
        ],
        "mongodb": [
                "dependencies": ["org.springframework.boot:spring-boot-starter-data-mongodb"],
                "classes": ["MongoConfig.java"],
                "yamlKeys": [
                        "spring.data.mongodb",
                        "MONGODB_URI",
                        "logging.level.org.springframework.data.mongodb.core.MongoTemplate"
                ],
                "dockerComposeKeys": ["services.mongodb"],
                // mongodb ids are Strings, jpa ids are Longs
                "searchAndReplace": [
                        [applicationYmlPath, "org.apache.kafka.common.serialization.LongSerializer", "org.apache.kafka.common.serialization.StringSerializer", false],
                        [applicationYmlPath, "org.apache.kafka.common.serialization.LongDeserializer", "org.apache.kafka.common.serialization.StringDeserializer", false],
                ]
        ],
        "streams": [
                "isHidden": true, // not prompted, activated by any of its children
                "dependencies": ["org.springframework.cloud:spring-cloud-dependencies"],
                "pomProperties": ["spring-cloud.version"],
                "yamlKeys": ["spring.cloud.stream"],
        ],
        "kafka": [
                "parents": ["streams"],
                "dependencies": [
                        "org.springframework.cloud:spring-cloud-starter-stream-kafka",
                        "org.springframework.cloud:spring-cloud-stream-schema"
                ],
                "pomProperties": ["spring-cloud-stream-schema.version", "avro.version"],
                "yamlKeys": [
                        "spring.kafka", "spring.cloud.stream.kafka.binder.enable-observation",
                        "KAFKA_BOOTSTRAP_SERVERS", "logging.level.org.apache.kafka.clients.admin"
                ],
                "dockerComposeKeys": ["services.zookeeper", "services.kafka", "services.schema-registry"]
        ],
        "modulith": [
                "parents": ["kafka"], // the transactional outbox externalizes events through spring-cloud-stream
                "dependencies": [
                        "org.springframework.modulith:spring-modulith-bom",
                        "org.springframework.modulith:spring-modulith-starter-core",
                        "org.springframework.modulith:spring-modulith-runtime",
                        "io.zenwave360.sdk:spring-modulith-events-scs",
                        "org.springframework.modulith:spring-modulith-test",
                        "org.springframework.modulith:spring-modulith-docs",
                ],
                "pomProperties": ["spring-modulith.version", "zenwave-modulith-events-scs.version"],
                "classes": ["ExternalizationConfiguration.java"],
                "yamlKeys": ["spring.modulith"],
        ],
        "jmolecules": [
                "dependencies": [
                        "org.jmolecules:jmolecules-bom",
                        "org.jmolecules:jmolecules-ddd",
                        "org.jmolecules:jmolecules-events",
                        "org.jmolecules:jmolecules-hexagonal-architecture",
                        "org.jmolecules:jmolecules-layered-architecture",
                        "org.jmolecules.integrations:jmolecules-archunit",
                ],
                "pomProperties": ["jmolecules-bom.version"],
        ],
        "modulith+jpa": [
                "dependencies": ["org.springframework.modulith:spring-modulith-starter-jdbc"],
                // the jdbc event publication registry schema only applies to jpa
                "yamlKeys": ["spring.modulith.events.jdbc"],
        ],
        "modulith+mongodb": [
                "dependencies": ["org.springframework.modulith:spring-modulith-starter-mongodb"],
        ],
]

// Maven's own interactive support for archetype properties is unusable, see https://issues.apache.org/jira/browse/ARCHETYPE-308
println "\nInteractive mode: ${request.properties.get("interactiveMode")}"
if (request.properties.get("interactiveMode") == "true") {
    println "Select the technologies you want to include in your project: (y/n)\n"
    // System.console() is null under piped input, IDE terminals and some JDK/terminal
    // combinations on Windows, so fall back to System.in instead of throwing an NPE
    def console = System.console()
    def stdin = console == null ? new BufferedReader(new InputStreamReader(System.in)) : null
    //noinspection GroovyAssignabilityCheck
    config.each { feature ->
        if (feature.key.contains('+') || feature.value.isHidden) return
        def question = "[FEATURE] Support for ${feature.key.toUpperCase()}? "
        def answer
        if (console != null) {
            answer = console.readLine(question)
        }
        else {
            print question
            System.out.flush()
            answer = stdin.readLine()
            if (answer == null) {
                throw new RuntimeException("Cannot read answers: this terminal exposes no java.io.Console "
                        + "and stdin is empty. Use -DaskForDefaultPropertyValues=true so that Maven asks "
                        + "the questions instead, or pass the features explicitly, e.g. -Djpa=true -Dkafka=true.")
            }
        }
        request.properties.put(feature.key, (answer ?: "").trim())
    }
    println ""
}

//===== Persistence: exactly one of jpa / mongodb =====

def jpaSelected = hasProperty(request, "jpa")
def mongodbSelected = hasProperty(request, "mongodb")

if (jpaSelected && mongodbSelected) {
    throw new RuntimeException("Select either 'jpa' or 'mongodb', not both: a ZDL model has a single 'persistence' setting.")
}
if (!jpaSelected && !mongodbSelected) {
    log "* No persistence selected, defaulting to JPA."
    request.properties.put("jpa", "true")
    jpaSelected = true
}
def persistence = mongodbSelected ? "mongodb" : "jpa"
log "* Persistence: ${persistence}"

// keep the ZDL model and the ZenWave scripts matching the selected persistence
["zenwave-model": "zdl", "zenwave-scripts": "zw"].each { name, extension ->
    ["jpa", "mongodb"].each { variant ->
        Path variantPath = projectPath.resolve("${name}-${variant}.${extension}")
        if (variant == persistence) {
            log "    Using ${variantPath.fileName} as ${name}.${extension}"
            Files.move(variantPath, projectPath.resolve("${name}.${extension}"))
        }
        else {
            Files.delete(variantPath)
        }
    }
}

//===== Feature parents: enable transitively =====

boolean parentsChanged = true
while (parentsChanged) {
    parentsChanged = false
    //noinspection GroovyAssignabilityCheck
    config.each { feature ->
        if (feature.value.parents && hasProperty(request, feature.key)) {
            feature.value.parents.each { parent ->
                if (!hasProperty(request, parent)) {
                    log "* Setting ${parent} to true because ${feature.key} is true..."
                    request.properties.put(parent, "true")
                    parentsChanged = true
                }
            }
        }
    }
}
println ""

// Adding a .gitignore file via maven-resources-plugin is broken
// see https://issues.apache.org/jira/browse/ARCHETYPE-505
Files.move(projectPath.resolve("_gitignore"), projectPath.resolve(".gitignore"))
// the archetype's own README.md is not shipped, `_README.md` is the one for generated projects
Files.move(projectPath.resolve("_README.md"), projectPath.resolve("README.md"))

//===== Remove everything that was not selected =====

def pomLines = pomFile.readLines()
def applicationYml = applicationYmlPath.readLines()
def applicationLocalYml = applicationLocalYmlPath.readLines()
def applicationTestYml = applicationTestYmlPath.readLines()
def dockerComposeYml = dockerComposeYmlPath.readLines()

//noinspection GroovyAssignabilityCheck
config.each { feature ->
    if (!hasProperty(request, feature.key)) {
        log "* Removing ${feature.key.toUpperCase()} from project..."
        feature.value["dependencies"]?.each { dependency ->
            log "    Removing dependency: ${dependency}"
            pomLines = removeDependencyFromPom(pomLines, dependency)
        }
        feature.value["pomProperties"]?.each { property ->
            log "    Removing pom property: ${property}"
            pomLines = removePropertyFromPom(pomLines, property)
        }
        feature.value["classes"]?.each { clazz ->
            log_no_newline "    Removing class: ${javaConfigPackageFolder}/${clazz}... "
            log projectPath.resolve(javaConfigPackageFolder).resolve(clazz).toFile().delete()
        }
        feature.value["yamlKeys"]?.each { key ->
            log "    Removing application.yml (default and test) key: ${key}"
            applicationYml = removeKeyFromYaml(applicationYml, key)
            applicationLocalYml = removeKeyFromYaml(applicationLocalYml, key)
            applicationTestYml = removeKeyFromYaml(applicationTestYml, key)
        }
        feature.value["dockerComposeKeys"]?.each { key ->
            log "    Removing docker-compose.yml key: ${key}"
            dockerComposeYml = removeKeyFromYaml(dockerComposeYml, key)
        }
    }
}

println ""

pomLines = removeEmptyDependencyManagement(pomLines)
applicationYml = removeEmptyParentKeys(applicationYml)
applicationLocalYml = removeEmptyParentKeys(applicationLocalYml)
applicationTestYml = removeEmptyParentKeys(applicationTestYml)
dockerComposeYml = removeEmptyParentKeys(dockerComposeYml)

writeLines(pomFile, pomLines)
writeLines(applicationYmlPath, applicationYml)
writeLines(applicationLocalYmlPath, applicationLocalYml)
writeLines(applicationTestYmlPath, applicationTestYml)
writeLines(dockerComposeYmlPath, dockerComposeYml)

//===== Tagged lines: `... // [feature]` =====

taggedLineFiles.each { file ->
    if (!file.exists()) return
    def kept = []
    file.readLines().each { line ->
        boolean drop = false
        String result = line
        //noinspection GroovyAssignabilityCheck
        config.each { feature ->
            if (feature.key.contains('+')) return
            def tag = ["// [${feature.key}]", "<!-- [${feature.key}] -->"].find { result.trim().endsWith(it) }
            if (tag == null) return
            if (hasProperty(request, feature.key)) {
                result = result.substring(0, result.lastIndexOf(tag)).replaceAll(/\s+$/, '')
            } else {
                drop = true
            }
        }
        if (!drop) {
            kept.add(result)
        }
    }
    writeLines(file, kept)
}

//===== Search and replace =====

//noinspection GroovyAssignabilityCheck
config.each { feature ->
    def selected = hasProperty(request, feature.key)
    def replacements = selected ? feature.value["searchAndReplace"] : feature.value["searchAndReplaceIfMissing"]
    if (replacements) {
        log "    Searching and replacing values for ${feature.key.toUpperCase()} (selected: ${selected})..."
        replacements.each { values ->
            searchAndReplace(values[0], values[1], values[2], values[3])
        }
    }
}

log ""

// Init git repository and commit changes (pass -Dgit=false to skip; the archetype ITs do,
// because on Windows read-only .git objects make a later `mvn clean` fail)
if (hasProperty(request, "git")) {
    log "* Initializing git and commiting changes..."
    executeGitCommand(["git", "init", "."] as String[], projectPath)
    executeGitCommand(["git", "add", "-A"] as String[], projectPath)
    executeGitCommand(["git", "commit", "-m", "Initial commit from archetype."] as String[], projectPath)
}
else {
    log "* Skipping git init (git=false)"
}

log "\n* Done!\n"
log "  Next steps:"
log "    cd ${request.artifactId}"
log "    docker compose up -d"
log "    # edit zenwave-model.zdl, then run the plugins in zenwave-scripts.zw"
log "    ./mvnw verify\n"

//===== Utilities =====

static boolean hasProperty(request, key) {
    String[] keys = key.split("\\+")
    for (String k : keys) {
        if (!['true', 't', 'T', 'yes', 'y', 'Y'].contains(request.properties.get(k))) {
            return false
        }
    }
    return true
}

static void searchAndReplace(File file, String search, String replace, boolean regex = true) {
    def fileText = file.text
    fileText = fileText.replaceAll('\r\n', '\n')
    if (regex) {
        fileText = fileText.replaceAll(search, replace)
    } else {
        fileText = fileText.replace(search, replace)
    }
    file.write(fileText)
}

static List<String> removeDependencyFromPom(List<String> pomLines, String dependency) {
    def (groupId, artifactId) = dependency.split(':')
    def newLines = []
    int i = 0

    while (i < pomLines.size()) {
        def line = pomLines[i]
        if (line.trim() == '<dependency>') {
            def groupIdLine = pomLines[i + 1].trim()
            def artifactIdLine = pomLines[i + 2].trim()
            if (groupIdLine == "<groupId>${groupId}</groupId>" && artifactIdLine == "<artifactId>${artifactId}</artifactId>") {
                while (pomLines[i++].trim() != '</dependency>') {
                    // skip
                }
                // remove previous comment line
                if (!newLines.isEmpty() && newLines[newLines.size() - 1].trim().startsWith("<!--")) {
                    newLines.remove(newLines.size() - 1)
                }
                continue
            }
        }
        newLines.add(line)
        i++
    }

    return newLines
}

/** Drops a <dependencyManagement> block left without any managed dependency. */
static List<String> removeEmptyDependencyManagement(List<String> pomLines) {
    int start = pomLines.findIndexOf { it.trim() == '<dependencyManagement>' }
    if (start < 0) {
        return pomLines
    }
    int end = pomLines.findIndexOf(start) { it.trim() == '</dependencyManagement>' }
    if (end < 0) {
        return pomLines
    }
    def body = pomLines[(start + 1)..(end - 1)]*.trim().findAll { !it.isEmpty() }
    if (body != ['<dependencies>', '</dependencies>']) {
        return pomLines
    }
    // also swallow one trailing blank line so the pom does not grow a gap
    if (end + 1 < pomLines.size() && pomLines[end + 1].trim().isEmpty()) {
        end++
    }
    def newLines = []
    newLines.addAll(pomLines[0..<start])
    newLines.addAll(pomLines[(end + 1)..<pomLines.size()])
    return newLines
}

static List<String> removePropertyFromPom(List<String> pomLines, String property) {
    def open = "<${property}>"
    def close = "</${property}>"
    return pomLines.findAll { !(it.trim().startsWith(open) && it.trim().endsWith(close)) }
}

/**
 * Removes `key` and everything nested below it. Matches both nested keys and flattened
 * dotted keys, so `spring.jpa` removes `spring: jpa: ...` and `spring.jpa.hibernate.ddl-auto: none`.
 */
static List<String> removeKeyFromYaml(List<String> ymlLines, String key) {
    List<String> newLines = []
    List<String> context = []
    int currentIndent = 0
    int i = 0
    while (i < ymlLines.size()) {
        String line = ymlLines[i]
        final int indent = countLeadingSpaces(line)
        String currentLineKey = line.split(":")[0].trim()
        // work out the context: where in the tree we are
        if (!line.trim().isEmpty() && !line.trim().startsWith('-') && !line.trim().startsWith("#")) { // arrays and comments do not change context
            if (indent == currentIndent) {
                if (!context.isEmpty()) {
                    context.remove(context.size() - 1)
                }
                context.add(currentLineKey)
            }
            else if (indent > currentIndent) {
                currentIndent = indent
                context.add(currentLineKey)
            }
            else if (indent < currentIndent) {
                while (indent <= currentIndent) {
                    if (!context.isEmpty()) {
                        context.remove(context.size() - 1)
                    }
                    currentIndent = currentIndent - 2
                }
                if (!currentLineKey.isEmpty()) {
                    currentIndent = indent
                    context.add(currentLineKey)
                }
            }
        }

        String path = context.join(".")
        if (path == key || path.startsWith(key + ".")) {
            i++
            int trailingBlanks = 0
            while (i < ymlLines.size() && (ymlLines[i].trim().isEmpty() || countLeadingSpaces(ymlLines[i]) > indent)) {
                trailingBlanks = ymlLines[i].trim().isEmpty() ? trailingBlanks + 1 : 0
                i++
            }
            // keep the blank lines that separated this block from the next one,
            // unless they would now sit right under the parent key
            def previous = newLines.isEmpty() ? "" : newLines[newLines.size() - 1].trim()
            if (!previous.endsWith(":")) {
                trailingBlanks.times { newLines.add("") }
            }
        }
        else {
            newLines.add(line)
            i++
        }
    }

    return newLines
}

/** Drops `parent:` lines left without children after removals. */
static List<String> removeEmptyParentKeys(List<String> lines) {
    boolean changed = true
    while (changed) {
        changed = false
        List<String> newLines = []
        for (int i = 0; i < lines.size(); i++) {
            String line = lines[i]
            if (line.trim() ==~ /[\w.\-]+:/) {
                int indent = countLeadingSpaces(line)
                int j = i + 1
                while (j < lines.size() && lines[j].trim().isEmpty()) {
                    j++
                }
                if (j >= lines.size() || countLeadingSpaces(lines[j]) <= indent) {
                    changed = true
                    continue
                }
            }
            newLines.add(line)
        }
        lines = newLines
    }
    return lines
}

def executeGitCommand(String[] command, Path projectPath) {
    try {
        log command.join(' ')
        def processBuilder = new ProcessBuilder(command)
        processBuilder.directory(projectPath.toFile())
        processBuilder.redirectErrorStream(true) // Redirect error stream to the output stream
        def process = processBuilder.start()
        def output = process.inputStream.text.trim()
        process.waitFor() // Wait for the command to finish
        log(output)
        if (process.exitValue() == 0) {
            return output
        } else {
            log("Git command execution failed")
        }
    } catch (Exception e) {
        log("An error occurred while executing the Git command: ${e.message}")
    }
    return null
}

static int countLeadingSpaces(String line) {
    return line.length() - line.trim().length()
}

static writeLines(File file, List<String> lines) {
    file.withWriter { writer ->
        lines.each { writer.println it }
    }
}

static log_no_newline(message) {
    print message
}

static log(message) {
    println message
}
