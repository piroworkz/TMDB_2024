plugins {
    alias(libs.plugins.kotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreData.coreDataRepositories)
    implementation(projects.feature.videos.videosDomain)
    implementation(libs.kotlinCoroutinesCore)
    implementation(libs.arrowCore)
    implementation(libs.javaxInject)
}