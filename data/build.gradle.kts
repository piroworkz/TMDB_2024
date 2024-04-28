plugins {
    alias(libs.plugins.kotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":usecases"))
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.arrow.core)
    implementation(libs.javaxInject)
}