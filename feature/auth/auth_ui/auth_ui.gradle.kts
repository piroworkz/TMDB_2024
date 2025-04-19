import com.davidluna.tmdb.convention.constants.Constants

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".auth_ui")
}

dependencies {
    
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreUi)
    implementation(projects.feature.auth.authDomain)
    implementation(libs.biometric)
    testImplementation(projects.testShared)
    testImplementation(projects.feature.auth.authFramework)
    testImplementation(projects.core.coreFramework)
}