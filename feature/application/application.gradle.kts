plugins {
    alias(libs.plugins.frameworkModuleConventionPlugin)
}

dependencies {
    api(projects.core.coreUi)
    api(projects.core.coreDomain)
    api(projects.core.coreData)
    api(projects.feature.auth.authUi)
    api(projects.feature.auth.authDomain)
    api(projects.feature.auth.authData)
    api(projects.feature.media.mediaUi)
    api(projects.feature.media.mediaDomain)
    api(projects.feature.media.mediaData)
    implementation(libs.playServicesLocation)
    implementation(libs.datastoreCore)
}