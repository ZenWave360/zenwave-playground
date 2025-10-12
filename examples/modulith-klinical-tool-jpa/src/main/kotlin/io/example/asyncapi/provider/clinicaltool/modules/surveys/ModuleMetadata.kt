package io.example.asyncapi.provider.clinicaltool.modules.surveys

import org.springframework.modulith.ApplicationModule
import org.springframework.modulith.PackageInfo

@PackageInfo
@ApplicationModule(displayName = "Clinical Surveys Backend", allowedDependencies = ["common"])
class ModuleMetadata
