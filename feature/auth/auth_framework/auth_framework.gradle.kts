import com.davidluna.tmdb.convention.constants.Constants

plugins {
    alias(libs.plugins.frameworkModuleConventionPlugin)
}


android {
    namespace = Constants.NAMESPACE.plus(".auth_framework")
}
dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreFramework)
    implementation(projects.feature.auth.authDomain)
    testImplementation(projects.testShared)
}