plugins {
    alias(libs.plugins.kotlinModuleConventionPlugin)
}

dependencies {
    implementation(projects.feature.core.coreDomain)
    testImplementation(libs.mockk)
}