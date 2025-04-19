import com.davidluna.tmdb.convention.constants.Constants

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".videos_ui")
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

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
    metricsDestination = layout.buildDirectory.dir("compose_compiler")
}