plugins {
    alias(libs.plugins.testSharedConventionPlugin)
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.feature.media.mediaDomain)
    implementation(projects.feature.auth.authDomain)
}