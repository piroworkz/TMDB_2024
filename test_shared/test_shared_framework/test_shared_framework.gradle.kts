import com.davidluna.architectcoders2024.build_logic.constants.Constants.NAMESPACE

plugins {
    alias(libs.plugins.frameworkModuleConventionPlugin)
    alias(libs.plugins.protobufPlugin)
}

android {
    namespace = NAMESPACE.plus(".test_shared_framework")
}

dependencies {
    implementation(projects.testShared.testSharedDomain)
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreData.coreDataFramework)
    implementation(projects.core.coreData.coreDataRepositories)
    implementation(projects.feature.auth.authDomain)
    implementation(projects.feature.auth.authData.authDataFramework)
    implementation(projects.feature.auth.authData.authDataRepositories)
    implementation(projects.feature.media.mediaDomain)
    implementation(projects.feature.media.mediaData.mediaDataFramework)
    implementation(projects.feature.media.mediaData.mediaDataRepositories)
    implementation(projects.feature.videos.videosDomain)
    implementation(projects.feature.videos.videosData.videosDataFramework)
    implementation(projects.feature.videos.videosData.videosDataRepositories)

    implementation(libs.datastoreCore)
    implementation(libs.protobufJavalite)
    implementation(libs.protobufKotlinLite)
    implementation(libs.coroutinesTest)
    implementation(libs.junit)
}
