plugins {
    alias(libs.plugins.kotlinModuleConventionPlugin)
}

dependencies {
    implementation(projects.core.coreDomain)
    testImplementation(projects.testShared)
}