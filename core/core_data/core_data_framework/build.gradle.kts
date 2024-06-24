import com.davidluna.architectcoders2024.build_logic.constants.Constants
import com.google.protobuf.gradle.id

plugins {
    alias(libs.plugins.frameworkModuleConventionPlugin)
    alias(libs.plugins.protobufPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".core_data_framework")
}

dependencies {
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreData.coreDataRepositories)

    implementation(libs.datastoreCore)
    implementation(libs.protobufJavalite)
    implementation(libs.protobufKotlinLite)
    implementation(libs.playServicesLocation)
    implementation(libs.javaxInject)
    implementation(libs.okhttpClient)
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