package io.zenwave360.example.clinicaltool;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ModulithTest {

    ApplicationModules modules = ApplicationModules.of(Application.class);

    @Test
    void verifyModules() {
        modules.verify();
    }

    @Test
    void createModuleDocumentation() {
        new Documenter(modules)
                .writeDocumentation();
    }

}
