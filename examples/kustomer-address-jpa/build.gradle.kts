
group = "io.zenwave360.examples"
version = "1.0.0-SNAPSHOT"

// Replace property references with hardcoded values
val basePackage = "io.zenwave360.examples.kotlin"
val openApiApiPackage = "$basePackage.web"
val openApiModelPackage = "$basePackage.web.dtos"
val asyncApiModelPackage = "$basePackage.events.dtos"
val asyncApiProducerApiPackage = "$basePackage.events"
val asyncApiConsumerApiPackage = "$basePackage.commands"

ext {
    set("spring-cloud.version", "2025.0.0")
    set("spring-cloud-stream-schema.version", "2.2.1.RELEASE")
    set("jakarta.validation-api.version", "3.1.1")
    set("mapstruct.version", "1.6.3")
    set("karate.version", "1.4.1")
    set("archunit-junit5.version", "1.4.0")
    set("springdoc-openapi-starter-webmvc-ui.version", "2.8.3")
}

plugins {
    java
    val kotlinVersion = "2.2.0-RC"
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false
    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    val springBootVersion = "3.5.0"
    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "7.11.0"
    id("dev.jbang") version "0.3.0-SNAPSHOT"
//    id("com.ncorti.ktfmt.gradle") version "0.16.0"
}

// Add ktfmt configuration
//ktfmt {
//    kotlinLangStyle()
//}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("spring-cloud.version")}")
    }
}


dependencies {
    // Add Kotlin dependencies
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Spring Boot starters
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aop")

    // JPA
    testImplementation("org.hibernate.orm:hibernate-ant")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")

    // Kafka
    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka")
    implementation("org.springframework.cloud:spring-cloud-stream-schema:${property("spring-cloud-stream-schema.version")}")

    // Distributed logs
    implementation("io.micrometer:micrometer-tracing")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    implementation("io.opentelemetry:opentelemetry-exporter-zipkin")

    // Utils
    implementation("jakarta.validation:jakarta.validation-api:${property("jakarta.validation-api.version")}")
    implementation("org.mapstruct:mapstruct:${property("mapstruct.version")}")
    kapt("org.mapstruct:mapstruct-processor:${property("mapstruct.version")}")
    implementation("org.apache.commons:commons-lang3")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.3")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework:spring-webflux")
    testImplementation("com.intuit.karate:karate-core:1.4.1")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("com.tngtech.archunit:archunit-junit5-api:1.4.0")
    testImplementation("com.tngtech.archunit:archunit-junit5-engine:1.4.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Make the compileKotlin task depend on openApiGenerate
tasks.named("compileKotlin") {
    dependsOn("openApiGenerate")
    dependsOn("generateAsyncApiDtos")
    dependsOn("generateAsyncApiProvider")
}

tasks.withType(org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask::class.java).configureEach {
    dependsOn("openApiGenerate")
}


openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("${projectDir}/src/main/resources/public/apis/openapi.yml")
    outputDir.set(layout.buildDirectory.dir("generated-sources/openapi").get().asFile.absolutePath)
//    skipIfSpecIsUnchanged.set(true)
    apiPackage.set(openApiApiPackage)
    modelPackage.set(openApiModelPackage)
    modelNameSuffix.set("DTO")
    typeMappings.set(mapOf("Double" to "java.math.BigDecimal"))
    configOptions.set(
        mapOf(
            "useSpringBoot3" to "true",
            "documentationProvider" to "none",
            "openApiNullable" to "false",
            "useOptional" to "false",
            "useTags" to "true",
            "interfaceOnly" to "true",
            "skipDefaultInterface" to "true",
            "delegatePattern" to "false",
            "sortParamsByRequiredFlag" to "false",
            "exceptionHandler" to "false"
        )
    )
}

// Add this after your other tasks
tasks.register<dev.jbang.gradle.tasks.JBangTask>("generateAsyncApiDtos") {
    group = "zenwave"
    description = "Generates Java DTOs from AsyncAPI specification"
    script.set("io.zenwave360.sdk:zenwave-sdk-cli:RELEASE")
    options.set(listOf(
        "--deps=org.slf4j:slf4j-simple:1.7.36,io.zenwave360.sdk.plugins:asyncapi-jsonschema2pojo:RELEASE"
    ))
    args.set(listOf(
        "-p", "jsonschema2pojo",
        "apiFile=src/main/resources/public/apis/asyncapi.yml",
        "targetFolder=${layout.buildDirectory.dir("generated-sources/zenwave").get().asFile.absolutePath}",
        "modelPackage=$asyncApiModelPackage",
        "jsonschema2pojo.isUseJakartaValidation=true",
        "jsonschema2pojo.useLongIntegers=true",
        "jsonschema2pojo.includeAdditionalProperties=true"
    ))
}

tasks.register<dev.jbang.gradle.tasks.JBangTask>("generateAsyncApiProvider") {
    group = "zenwave"
    description = "Generates Spring Cloud Streams code from AsyncAPI specification"
    script.set("io.zenwave360.sdk:zenwave-sdk-cli:RELEASE")
    options.set(listOf(
        "--deps=org.slf4j:slf4j-simple:1.7.36,io.zenwave360.sdk.plugins:asyncapi-spring-cloud-streams3:RELEASE",
        "--java-options \"--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED\"",
        "--java-options \"--add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED\"",
        "--java-options \"--add-exports=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED\"",
        "--java-options \"--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED\"",
        "--java-options \"--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED\""
    ))
    args.set(listOf(
        "-p", "spring-cloud-streams3",
        "apiFile=src/main/resources/public/apis/asyncapi.yml",
        "targetFolder=${layout.buildDirectory.dir("generated-sources/zenwave").get().asFile.absolutePath}",
        "role=provider",
        "style=imperative",
        "modelPackage=$asyncApiModelPackage",
        "producerApiPackage=$asyncApiProducerApiPackage",
        "consumerApiPackage=$asyncApiConsumerApiPackage"
    ))
}


sourceSets {
    main {
        java {
            srcDir(layout.buildDirectory.dir("generated-sources/openapi/src/main/kotlin").get().asFile)
            srcDir(layout.buildDirectory.dir("generated-sources/zenwave/src/main/java").get().asFile)
        }
    }
    test {
        java {
            srcDir(layout.buildDirectory.dir("generated-sources/zenwave/src/test/java").get().asFile)
        }
    }
}

