plugins {
    alias(libs.plugins.architectCodersAndroidApplication)
}

dependencies {
    implementation(projects.di)
    implementation(projects.navigation)
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(projects.feature.main.mainUi)

}

kapt {
    correctErrorTypes = true
}