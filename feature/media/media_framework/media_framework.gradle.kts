import com.davidluna.tmdb.convention.constants.Constants

plugins {
    alias(libs.plugins.frameworkModuleConventionPlugin)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = Constants.NAMESPACE.plus(".media_framework")
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreFramework)
    implementation(projects.feature.media.mediaDomain)
    testImplementation(projects.testShared)
}