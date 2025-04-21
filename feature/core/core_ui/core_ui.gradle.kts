import com.davidluna.tmdb.convention.constants.Constants.NAMESPACE

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = NAMESPACE.plus(".core_ui")
}

dependencies {
    implementation(projects.feature.core.coreDomain)
    implementation(libs.coilCompose)
    implementation(libs.core.splashscreen)
}