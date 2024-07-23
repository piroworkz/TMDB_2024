import com.davidluna.architectcoders2024.build_logic.constants.Constants.NAMESPACE

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = NAMESPACE.plus(".core_ui")
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(libs.fragmentKtx)
    implementation(libs.coilCompose)
    testImplementation(projects.testShared.testSharedDomain)
}