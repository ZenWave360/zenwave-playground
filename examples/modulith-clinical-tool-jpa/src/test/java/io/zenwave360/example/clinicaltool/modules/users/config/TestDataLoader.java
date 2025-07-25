package io.zenwave360.example.clinicaltool.modules.users.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Table;

import java.io.File;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDataLoader {

    private List<? extends Class<?>> jpaManagedTypes;

    private final ObjectMapper objectMapper = new ObjectMapper();
    {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public TestDataLoader(List<? extends Class<?>> jpaManagedTypes) {
        this.jpaManagedTypes = jpaManagedTypes;
    }

    public <T> List<T> loadCollectionTestDataAsObjects(Class<T> collectionClass) {
        var jsonList = loadCollectionTestDataAsJson(collectionClass);
        return jsonList.stream().map(json -> read(collectionClass, json)).collect(Collectors.toList());
    }

    public List<String> loadCollectionTestDataAsJson(Class collectionClass) {
		var annotation = (Table) collectionClass.getAnnotation(Table.class);
		var table = annotation.name();
        return readDirectoryFilesAsString("src/test/resources/data/jpa/" + table);
    }

    protected List<String> listFolders(String directory) {
        return Stream.of(new File(directory).listFiles()).map(File::getName).collect(Collectors.toList());
    }

    protected List<String> readDirectoryFilesAsString(String directory) {
        return Stream.of(new File(directory).listFiles()).map(f -> {
            try {
                return Files.readString(f.toPath());
            } catch (java.io.IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public <T> T read(Class<T> type, String json) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error reading json test data for " + type.getName(), e);
        }
    }

}
