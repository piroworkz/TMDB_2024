plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

dependencies {
    
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreUi)
    implementation(projects.feature.auth.authDomain)
    testImplementation(projects.testShared)
    testImplementation(projects.feature.auth.authFramework)
    testImplementation(projects.core.coreFramework)
}