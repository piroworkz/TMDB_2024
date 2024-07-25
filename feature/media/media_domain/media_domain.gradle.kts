plugins {
    alias(libs.plugins.kotlinModuleConventionPlugin)
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.feature.videos.videosDomain)
    testImplementation(projects.testShared)
}