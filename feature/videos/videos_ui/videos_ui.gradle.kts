plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

dependencies {
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(projects.feature.videos.videosDomain)
    
    implementation(libs.pagingCompose)
    testImplementation(projects.testShared)
    testImplementation(projects.feature.videos.videosFramework)
    testImplementation(projects.core.coreFramework)
}
