import com.davidluna.tmdb.convention.constants.Constants.NAMESPACE

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = NAMESPACE.plus(".core_ui")
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(libs.coilCompose)
    testImplementation(projects.testShared)
}