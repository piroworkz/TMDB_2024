plugins {
    alias(libs.plugins.frameworkModuleConventionPlugin)
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreData)
    implementation(projects.feature.auth.authDomain)
    testImplementation(projects.testShared)
}