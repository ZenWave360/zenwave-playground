package io.zenwave360.example.clinicaltool.modules.documents.config

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import jakarta.persistence.Table
import java.io.File
import java.nio.file.Files
import java.util.stream.Collectors

class TestDataLoader(private val jpaManagedTypes: List<Class<*>>) {

    private val objectMapper = ObjectMapper().apply {
        registerModule(JavaTimeModule())
    }

    fun <T> loadCollectionTestDataAsObjects(collectionClass: Class<T>): List<T> {
        val jsonList = loadCollectionTestDataAsJson(collectionClass)
        return jsonList.map { json -> read(collectionClass, json) }
    }

    fun loadCollectionTestDataAsJson(collectionClass: Class<*>): List<String> {
        val annotation = collectionClass.getAnnotation(Table::class.java)
        val table = annotation.name
        return readDirectoryFilesAsString("src/test/resources/data/jpa/$table")
    }

    protected fun listFolders(directory: String): List<String> {
        return File(directory).listFiles()?.map { it.name } ?: emptyList()
    }

    protected fun readDirectoryFilesAsString(directory: String): List<String> {
        return File(directory).listFiles()?.map { f ->
            try {
                Files.readString(f.toPath())
            } catch (e: java.io.IOException) {
                throw RuntimeException(e)
            }
        } ?: emptyList()
    }

    fun <T> read(type: Class<T>, json: String): T {
        try {
            return objectMapper.readValue(json, type)
        } catch (e: JsonProcessingException) {
            throw RuntimeException("Error reading json test data for ${type.name}", e)
        }
    }
}