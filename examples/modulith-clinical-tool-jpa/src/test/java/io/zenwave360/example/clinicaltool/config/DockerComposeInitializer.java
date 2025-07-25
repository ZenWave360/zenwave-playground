package io.zenwave360.example.clinicaltool.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class DockerComposeInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Use this annotation to activate TestContainers in your test.
     */
    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @org.springframework.test.context.ContextConfiguration(initializers = DockerComposeInitializer.class)
    public @interface EnableDockerCompose {

    }

	private record Service(String name, int port, String envVar, String envValueTemplate) {}

	private static final String DOCKER_COMPOSE_FILE = "./docker-compose.yml";
	private static final List<Service> SERVICES = List.of(
		new Service("postgresql", 5432, "DATASOURCE_URL", "jdbc:postgresql://%s:%s/DATABASENAME"),
		new Service("kafka", 9092, "KAFKA_BOOTSTRAP_SERVERS", "%s:%s")
	);

	static String HOST = DockerClientFactory.instance().dockerHostIpAddress();
	static DockerComposeContainer container = new DockerComposeContainer(new File(DOCKER_COMPOSE_FILE)).withEnv("HOST", HOST);
	static {
		for (Service service : SERVICES) {
			if("schema-registry".equals(service.name)) {
				container.withExposedService(service.name, service.port, Wait.forHttp("/subjects").forStatusCode(200));
			}
			else {
				container.withExposedService(service.name, service.port, Wait.forListeningPort());
			}
		}
	}
	static boolean isContainerRunning = false;

    @Override
    public void initialize(ConfigurableApplicationContext ctx) {
        if(isDockerComposeRunningAllServices(SERVICES)) {
            log.info("Docker Compose Containers are running from local docker-compose. Skipping TestContainers...");
        } else {
            log.info("Docker Compose Containers are not running from local docker-compose. Starting from TestContainers...");
            if (isContainerRunning) {
                log.info("Docker Compose Containers are already running from TestContainers. Skipping...");
            } else {
				log.info("Starting Docker Compose Containers from TestContainers...");
				container.start();
				isContainerRunning = true;

				for (Service service : SERVICES) {
					int port = container.getServicePort(service.name, service.port);
					log.info("DockerCompose exposed port for {}: {}", service.name, HOST + ":" + port);
					log.info("DockerCompose Service {} listening: {}", service.name, isPortOpen(HOST, port));
					if (service.envValueTemplate != null) {
						TestPropertyValues.of(service.envVar + "=" +String.format(service.envValueTemplate, HOST, port))
								.applyTo(ctx.getEnvironment());
					}
				}
            }
        }
    }

	private boolean isDockerComposeRunningAllServices(List<Service> services) {
		var serviceNames = services.stream().map(Service::name).toList();
		return Stream.of("docker-compose", "docker-compose.exe").anyMatch(cmd -> {
			try {
				return getDockerComposeRunningServices(cmd, "-f", DOCKER_COMPOSE_FILE, "ps", "--services").containsAll(serviceNames);
			}
			catch (IOException | InterruptedException e) {
				return false;
			}
		});
	}

	private static final int SERVICE_COLUMN = 3;
	public List<String> getDockerComposeRunningServices(String... command) throws IOException, InterruptedException {
		List<String> services = new ArrayList<>();
		var process = new ProcessBuilder(command).start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String line;
		while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                services.add(line);
            }
		}

		process.waitFor();
		return services;
	}

    boolean isPortOpen(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
