import com.davidluna.architectcoders2024.build_logic.constants.Constants.NAMESPACE


plugins {
    alias(libs.plugins.androidLibraryConventionPlugin)
}

android {
    namespace = NAMESPACE.plus(".test_shared_framework")
}

dependencies {
    implementation(projects.testShared.testSharedDomain)
    implementation(projects.core.coreDomain)
    implementation(projects.feature.media.mediaDomain)
    implementation(projects.feature.videos.videosDomain)
    implementation(projects.feature.auth.authDomain)
    implementation(projects.core.coreData.coreDataFramework)
    implementation(projects.feature.auth.authData.authDataFramework)
    implementation(projects.feature.media.mediaData.mediaDataFramework)
    implementation(projects.feature.videos.videosData.videosDataFramework)
    implementation(libs.gson)
    implementation(libs.junit)
    implementation(libs.mockitoKotlin)
    implementation(libs.mockitoInline)
    implementation(libs.coroutinesTest)
    implementation(libs.turbine)
    implementation(libs.truth)
}