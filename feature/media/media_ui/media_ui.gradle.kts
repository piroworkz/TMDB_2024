import com.davidluna.architectcoders2024.build_logic.constants.Constants

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".media_ui")
}

dependencies {
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(projects.feature.media.mediaDomain)
    implementation(projects.navigation)
    implementation(libs.pagingCompose)
    implementation(libs.coilCompose)
    testImplementation(projects.testShared.testSharedDomain)
    testImplementation(projects.testShared.testSharedFramework)
    testImplementation("androidx.paging:paging-common:3.3.0")
    testImplementation("androidx.paging:paging-testing:3.3.0")

}