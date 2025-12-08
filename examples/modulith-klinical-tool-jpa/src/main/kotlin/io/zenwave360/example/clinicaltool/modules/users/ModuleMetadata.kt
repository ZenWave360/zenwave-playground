package io.zenwave360.example.clinicaltool.modules.users

import org.springframework.modulith.ApplicationModule
import org.springframework.modulith.PackageInfo

@PackageInfo @ApplicationModule(displayName = "User Management", allowedDependencies = ["common"]) class ModuleMetadata
