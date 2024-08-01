plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    implementation(libs.kotlinPoet)
    implementation(libs.symbolProcessingApi)
}