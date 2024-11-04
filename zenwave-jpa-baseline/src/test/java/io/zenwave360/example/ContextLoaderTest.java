package io.zenwave360.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
class ContextLoaderTest {

    static final Logger log = LoggerFactory.getLogger(ContextLoaderTest.class);

    static final List<Service> SERVICES = new ArrayList(
            List.of(new Service("postgresql", 5432, "DATASOURCE_URL", "jdbc:postgresql://%s:%s/app"),
                    new Service("mongodb", 27017, "MONGODB_URI", "mongodb://%s:%s/app?replicaSet=rs0"),
                    new Service("kafka", 9092, "KAFKA_BOOTSTRAP_SERVERS", "%s:%s")));

    static {
        var dockerServices = getServicesInDockerComposeFile();
        SERVICES.removeIf(service -> !dockerServices.contains(service.name));
    }

    static String HOST = DockerClientFactory.instance().dockerHostIpAddress();
    static DockerComposeContainer<?> container = new DockerComposeContainer(new File("./docker-compose.yml"))
        .withEnv("HOST", HOST);

    @BeforeAll
    static void setUpBeforeAll() throws IOException {
        for (Service service : SERVICES) {
            if ("schema-registry".equals(service.name)) {
                container.withExposedService(service.name, service.port, Wait.forHttp("/subjects").forStatusCode(200));
            }
            else {
                container.withExposedService(service.name, service.port, Wait.forListeningPort());
            }
        }
        log.info("Starting DockerCompose container");
        container.start();
        log.info("DockerCompose started");
    }

    @AfterAll
    static void tearDownAfterAll() {
        container.stop();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        log.info("Setting properties from DockerCompose containers");
        for (Service service : SERVICES) {
            int port = container.getServicePort(service.name, service.port);
            log.info("DockerCompose exposed port for {}: {}", service.name, HOST + ":" + port);
            if (service.envValueTemplate != null) {
                registry.add(service.envVar, () -> String.format(service.envValueTemplate, HOST, port));
            }
        }
    }

    @Test
    void loadsContext() {
        log.info("Context loaded");
        Assertions.assertTrue(true, "Context loaded");
    }

    record Service(String name, int port, String envVar, String envValueTemplate) {
    }

    private static Collection<String> getServicesInDockerComposeFile() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = Files.newInputStream(Paths.get("./docker-compose.yml"))) {
            Map<String, Object> yamlMap = yaml.load(inputStream);
            Map<String, Object> services = (Map<String, Object>) yamlMap.get("services");
            return services.keySet();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
