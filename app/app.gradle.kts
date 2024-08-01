plugins {
    alias(libs.plugins.architectCodersAndroidApplication)
}

dependencies {
    implementation(projects.navigation)
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(projects.main)
}