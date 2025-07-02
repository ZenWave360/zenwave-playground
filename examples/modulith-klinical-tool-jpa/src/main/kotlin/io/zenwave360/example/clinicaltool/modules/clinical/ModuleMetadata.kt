package io.zenwave360.example.clinicaltool.modules.clinical;

import org.springframework.modulith.ApplicationModule
import org.springframework.modulith.PackageInfo

@PackageInfo
@ApplicationModule(displayName = "Clinical Tool Backend", allowedDependencies = ["common"])
class ModuleMetadata
