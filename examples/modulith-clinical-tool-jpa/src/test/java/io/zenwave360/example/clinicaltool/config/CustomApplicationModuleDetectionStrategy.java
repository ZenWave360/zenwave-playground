package io.zenwave360.example.clinicaltool.config;

import com.tngtech.archunit.core.domain.JavaClass;
import org.springframework.modulith.core.*;

import java.util.HashSet;
import java.util.stream.Stream;

public class CustomApplicationModuleDetectionStrategy implements ApplicationModuleDetectionStrategy {

    public CustomApplicationModuleDetectionStrategy() {
        System.out.println("CustomApplicationModuleDetectionStrategy constructor called");
    }

    @Override
    public Stream<JavaPackage> getModuleBasePackages(JavaPackage basePackage) {
        return basePackage.getSubPackagesAnnotatedWith(org.springframework.modulith.ApplicationModule.class);
    }

    @Override
    public NamedInterfaces detectNamedInterfaces(JavaPackage basePackage, ApplicationModuleInformation information) {
        var modulePublicClasses = new HashSet<JavaClass>();
        var namedInterfaces = NamedInterfaces.discoverNamedInterfaces(basePackage);
        for (NamedInterface javaClass : namedInterfaces) {
            for (var interfaceClass : javaClass.asJavaClasses().toList()) {
                interfaceClass.getAllMethods().forEach(method -> {
                    method.getParameterTypes()
                            ;
                    method.getReturnType();
                    modulePublicClasses.add(interfaceClass);
                });
            }
        }
        return NamedInterfaces.discoverNamedInterfaces(basePackage);
    }
}
