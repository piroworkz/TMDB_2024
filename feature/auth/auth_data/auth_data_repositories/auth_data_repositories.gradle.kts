plugins {
    alias(libs.plugins.kotlinModuleConventionPlugin)
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreData.coreDataRepositories)
    implementation(projects.feature.auth.authDomain)
    testImplementation(projects.testShared.testSharedDomain)
}