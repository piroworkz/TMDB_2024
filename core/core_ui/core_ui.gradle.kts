plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(libs.coilCompose)
    testImplementation(projects.testShared)
}