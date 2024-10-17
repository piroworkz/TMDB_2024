plugins {
    alias(libs.plugins.architectCodersAndroidApplication)
}

dependencies {
    implementation(projects.feature.application)
//    implementation(projects.feature.splash)
//    implementation(projects.core.coreUi)
//    implementation(projects.core.coreDomain)
//    implementation(projects.core.coreData)
//    implementation(projects.feature.auth.authUi)
//    implementation(projects.feature.auth.authDomain)
//    implementation(projects.feature.auth.authData)
//    implementation(projects.feature.media.mediaUi)
//    implementation(projects.feature.media.mediaDomain)
//    implementation(projects.feature.media.mediaData)
//    implementation(projects.feature.videos.videosUi)
//    implementation(projects.feature.videos.videosDomain)
//    implementation(projects.feature.videos.videosFramework)
    testImplementation(projects.testShared)
    androidTestImplementation(projects.testShared)
}