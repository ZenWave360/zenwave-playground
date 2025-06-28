package io.zenwave360.example.clinicaltool;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModulithTest {

    ApplicationModules modules = ApplicationModules.of(Application.class);

    @BeforeAll
    static void setup() {
        // Set system property to use custom strategy
        System.setProperty(
                "org.springframework.modulith.core.ApplicationModuleDetectionStrategy",
                "io.zenwave360.example.clinicaltool.config.CustomApplicationModuleDetectionStrategy"
        );
    }

    @Test
    void verifyModules() {
        modules.verify();
    }

//    @Test
//    void createModuleDocumentation() {
//        new Documenter(modules)
//            .writeDocumentation();
//    }
}
