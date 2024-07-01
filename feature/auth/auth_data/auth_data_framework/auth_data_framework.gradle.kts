import com.davidluna.architectcoders2024.build_logic.constants.Constants

plugins {
    alias(libs.plugins.frameworkModuleConventionPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".auth_data_framework")
}
dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreData.coreDataFramework)
    implementation(projects.feature.auth.authDomain)
    implementation(projects.feature.auth.authData.authDataRepositories)
}