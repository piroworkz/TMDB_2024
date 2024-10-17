plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

dependencies {
    
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreUi)
    implementation(projects.feature.auth.authDomain)
    implementation(libs.composeAndroidPermissions)
    implementation(libs.biometric)
    testImplementation(projects.testShared)
    testImplementation(projects.feature.auth.authData)
    testImplementation(projects.core.coreData)
}