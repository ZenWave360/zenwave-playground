# ZenWave Java Archetype

A Maven archetype that generates modern ZenWave SDK projects — the same shape as
[zenwave-jpa-baseline](../zenwave-jpa-baseline) and [zenwave-mongodb-baseline](../zenwave-mongodb-baseline) —
letting you pick the technologies you want at generation time.

This project is **both** the archetype source and a working Spring Boot application: it is the
*superset* of every supported technology, and `archetype-post-generate.groovy` strips out whatever
you did not select.

## Features you can choose

| Feature | What it adds |
|---|---|
| `jpa` | `spring-boot-starter-data-jpa`, PostgreSQL, `DatabaseConfiguration`, Hikari datasource config, `postgresql` compose service |
| `mongodb` | `spring-boot-starter-data-mongodb`, `MongoConfig`, `spring.data.mongodb` config, `mongodb` compose service (single-node replica set) |
| `kafka` | `spring-cloud-starter-stream-kafka` + `spring-cloud-stream-schema-registry-client`, `spring.kafka`/`spring.cloud.stream` config, `zookeeper`/`kafka`/`schema-registry` compose services |
| `modulith` | Spring Modulith + transactional outbox: `spring-modulith-starter-core`, `-starter-jdbc`/`-starter-mongodb`, `io.zenwave360.sdk:spring-modulith-events-scs`, `ExternalizationConfiguration`, `spring.modulith.*` config, `spring-modulith-test`/`-docs`, `useSpringModulith`/`transactionalOutbox modulith` in `zenwave-scripts.zw`, and `transactionalOutbox`/`includeApplicationEventListener` in the AsyncAPIGenerator config. **Implies `kafka`.** |

Always included: Spring Web/Security/Actuator, springdoc, MapStruct, Lombok, Testcontainers +
ArchUnit tests, spotless/palantir + spring-javaformat, jacoco, `openapi-generator-maven-plugin`, and the
ZenWave SDK AsyncAPI generation profile.

### Rules

- **Exactly one of `jpa` / `mongodb`.** A ZDL model has a single `persistence` setting, so selecting
  both fails; selecting neither defaults to `jpa`.
- **`modulith` implies `kafka`** — the outbox externalizes events through Spring Cloud Stream.
- **AsyncAPI generation is always kept**, even without `kafka`. Deselecting `kafka` only removes the
  binder, its YAML and its compose services; `ZDLToAsyncAPIPlugin` and the `asyncapi-code-generation`
  profile stay, so events are generated as in-process producers. Add a binder later if you want them
  externalized.

## Using the archetype

```bash
mvn archetype:generate \
  -DarchetypeGroupId=io.zenwave360.archetypes \
  -DarchetypeArtifactId=zenwave-java-archetype \
  -DarchetypeVersion=0.0.1-SNAPSHOT \
  -DgroupId=com.example \
  -DartifactId=my-service \
  -Dpackage=com.example.myservice \
  -Dversion=0.0.1-SNAPSHOT \
  -DprojectName="My Service" \
  -DprojectDescription="My Service" \
  -Djpa=true -Dkafka=true -Dmodulith=true \
  -DinteractiveMode=false
```

### Interactively

```bash
mvn archetype:generate   -DarchetypeGroupId=io.zenwave360.archetypes   -DarchetypeArtifactId=zenwave-java-archetype   -DarchetypeVersion=0.0.1-SNAPSHOT   -DinteractiveMode=true
```

Maven asks for `projectName`, `projectDescription`, `artifactId`, `groupId` and `package` (the only
properties without a `<defaultValue>`), then the post-generate script asks the feature questions:

```
[FEATURE] Support for JPA? y
[FEATURE] Support for MONGODB? n
[FEATURE] Support for KAFKA? n
[FEATURE] Support for MODULITH? y
```

Accepted as yes: `y Y yes t T true`; anything else is no. Maven's own interactive support for these
is unusable ([ARCHETYPE-308](https://issues.apache.org/jira/browse/ARCHETYPE-308)), hence the
script asking them itself.

Do **not** add `-DaskForDefaultPropertyValues=true`: it makes Maven prompt for every property that
has a default — i.e. the whole feature list — and you get asked twice. It is only useful as a
fallback in a terminal where `System.console()` is unavailable and the `[FEATURE]` prompts cannot run.

To generate **into the current folder** rather than into a new `artifactId` subfolder, add
`-DoutputDirectory=..` and give `-DartifactId` the current folder's name. The folder may already
contain files, but it must not contain a `pom.xml` (Maven refuses) and must not contain a
`.gitignore` or `README.md` (the post-generate rename fails on them).

The generated project is git-initialised with an `Initial commit from archetype.` commit. Pass
`-Dgit=false` to skip that (the archetype ITs do, because on Windows the read-only objects git writes
make a later `mvn clean` fail).

## Building the archetype

Requires a POSIX shell (Git Bash or WSL on Windows) for `sed -i`:

```bash
./create-archetype.sh
```

```bash
(cd target/generated-sources/archetype && mvn clean install)
```

`create-archetype.sh` runs `archetype:create-from-project` and then post-processes the result:

1. rewrites `io.zenwave360.example` to `${package}` throughout `archetype-resources`
2. rewrites `ZenWave Java Archetype Baseapp` to `${projectName}` (pom `<name>`, springdoc title, `.zdl` titles)
3. copies `archetype/archetype-post-generate.groovy` and `archetype/archetype-metadata.xml` into the archetype
4. copies `archetype/tests/*` as the archetype's integration-test projects

Running `mvn install` on the generated archetype also runs those test projects
(`jpa`, `jpa+kafka`, `jpa+modulith`, `mongodb`, `mongodb+kafka`, `mongodb+modulith`).

## How the source project is laid out

| Path | Notes |
|---|---|
| `pom.xml` | superset of all dependencies; features are removed by `groupId:artifactId` |
| `src/main/resources/application.yml` | superset; features are removed by YAML key |
| `docker-compose.yml` | superset; not Velocity-filtered (it uses `${HOST:-localhost}`) |
| `zenwave-model-jpa.zdl` / `zenwave-model-mongodb.zdl` | one is renamed to `zenwave-model.zdl`, the other deleted |
| `zenwave-scripts-jpa.zw` / `zenwave-scripts-mongodb.zw` | same, renamed to `zenwave-scripts.zw` |
| `_gitignore`, `_README.md` | renamed by the post-generate script ([ARCHETYPE-505](https://issues.apache.org/jira/browse/ARCHETYPE-505)) |
| `archetype/` | archetype descriptor, post-generate script and test projects — never shipped |

### Shared with the Kotlin archetype

`archetype/archetype-post-generate.groovy` is **byte-identical** to the copy in
[zenwave-kotlin-archetype-baseapp](../zenwave-kotlin-archetype-baseapp): it finds `src/main/java` or
`src/main/kotlin` itself. Fix it in one archetype and copy it to the other; `create-archetype.sh` warns
when the two copies differ.

### Adding a new feature

Add an entry to the `config` map in `archetype/archetype-post-generate.groovy`, add its
`<requiredProperty>` to `archetype/archetype-metadata.xml`, and add its dependencies/config to the
superset files. Supported keys per feature:

| Key | Effect when the feature is **not** selected |
|---|---|
| `dependencies` | removes `groupId:artifactId` from `pom.xml` (works for `<dependencyManagement>` BOMs too) |
| `pomProperties` | removes `<property>` lines from `pom.xml` |
| `classes` | deletes classes (named without extension) from `src/main/java/<package>/config` |
| `yamlKeys` | removes keys from `application.yml`, `application-default.yml`, `application-test.yml` |
| `dockerComposeKeys` | removes keys from `docker-compose.yml` |
| `searchAndReplaceIfMissing` | `[file, search, replace, isRegex]` |
| `... // [feature]` / `... <!-- [feature] -->` line tags | removes tagged lines from `zenwave-scripts.zw` and `pom.xml`; when the feature *is* selected the line is kept with the tag stripped |

`searchAndReplace` runs when the feature **is** selected. `isHidden: true` keeps a feature out of the
interactive prompt; `parents: [...]` force-enables other features. An `a+b` key applies only when both
`a` and `b` are selected.
