plugins {
    alias(libs.plugins.kotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {
    implementation(libs.kotlinCoroutinesCore)
    implementation(libs.arrowCore)
    implementation(libs.javaxInject)
}