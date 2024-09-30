
import com.davidluna.tmdb.convention.constants.Constants
import com.google.protobuf.gradle.id

plugins {
    alias(libs.plugins.frameworkModuleConventionPlugin)
    alias(libs.plugins.protobufPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".core_framework")
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(libs.datastoreCore)
    implementation(libs.protobufJavalite)
    implementation(libs.protobufKotlinLite)
    implementation(libs.playServicesLocation)
    implementation(libs.okhttpClient)
    testImplementation(projects.testShared)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.12"
    }
    generateProtoTasks {
        all().forEach {
            it.builtins {
                id("java") {
                    option("lite")
                }

                id("kotlin") {
                    option("lite")
                }
            }
        }
    }
}