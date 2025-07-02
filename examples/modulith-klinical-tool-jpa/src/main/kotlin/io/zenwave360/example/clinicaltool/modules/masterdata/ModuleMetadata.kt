package io.zenwave360.example.clinicaltool.modules.masterdata;

import org.springframework.modulith.ApplicationModule
import org.springframework.modulith.PackageInfo

@PackageInfo
@ApplicationModule(displayName = "Master Data", allowedDependencies = ["common"])
class ModuleMetadata
