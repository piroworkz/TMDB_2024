plugins {
    alias(libs.plugins.architectCodersAndroidApplication)
    jacoco
    `jacoco-report-aggregation`
    `test-report-aggregation`
}

dependencies {
    implementation(projects.di)
    implementation(projects.navigation)
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(projects.feature.mainUi)
}