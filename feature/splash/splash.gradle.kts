plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

dependencies {
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(libs.composeAndroidPermissions)
    implementation(libs.biometric)
    testImplementation(projects.testShared)
    testImplementation(projects.core.coreFramework)
}