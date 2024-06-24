import java.util.Properties

plugins {
    alias(libs.plugins.architectCodersAndroidApplication)
}

val key: String = Properties().let {
    it.load(project.rootProject.file("local.properties").inputStream())
    it.getProperty("key")
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