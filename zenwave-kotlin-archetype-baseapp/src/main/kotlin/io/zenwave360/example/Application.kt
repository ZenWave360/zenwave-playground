package io.zenwave360.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator

@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator::class)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
