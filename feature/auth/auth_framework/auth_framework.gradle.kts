plugins {
    alias(libs.plugins.frameworkModuleConventionPlugin)
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreFramework)
    implementation(projects.feature.auth.authDomain)
    testImplementation(projects.testShared)
}