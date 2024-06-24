import com.davidluna.architectcoders2024.build_logic.constants.Constants

plugins {
    alias(libs.plugins.androidLibraryConventionPlugin)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hiltPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".di")

}

dependencies {
    implementation(projects.feature.splashUi)
    implementation(projects.feature.auth.authUi)
    implementation(projects.feature.auth.authDomain)
    implementation(projects.feature.auth.authData.authDataFramework)
    implementation(projects.feature.auth.authData.authDataRepositories)
    implementation(projects.feature.media.mediaDomain)
    implementation(projects.feature.media.mediaData.mediaDataFramework)
    implementation(projects.feature.media.mediaData.mediaDataRepositories)
    implementation(projects.feature.videos.videosUi)
    implementation(projects.feature.videos.videosDomain)
    implementation(projects.feature.videos.videosData.videosDataFramework)
    implementation(projects.feature.videos.videosData.videosDataRepositories)
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreData.coreDataFramework)
    implementation(projects.core.coreData.coreDataRepositories)

    implementation(libs.protobufJavalite)
    implementation(libs.protobufKotlinLite)

    implementation(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    implementation(libs.retrofit)
    implementation(libs.okhttpClient)
    implementation(libs.okhttpLoggingInterceptor)
    implementation(libs.kotlinxSerializationJson)
    implementation(libs.kotlinConverter)
    implementation(libs.kotlinCoroutinesCore)
    implementation(libs.datastoreCore)
    implementation(libs.playServicesLocation)

}