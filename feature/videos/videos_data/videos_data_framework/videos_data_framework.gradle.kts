import com.davidluna.architectcoders2024.build_logic.constants.Constants

plugins {
    alias(libs.plugins.frameworkModuleConventionPlugin)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = Constants.NAMESPACE.plus(".videos_data_framework")
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreData.coreDataFramework)
    implementation(projects.feature.videos.videosDomain)
    implementation(projects.feature.videos.videosData.videosDataRepositories)
}