
import com.davidluna.architectcoders2024.build_logic.constants.Constants
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.alias
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.implementation
import com.davidluna.architectcoders2024.build_logic.deps.unitTestingBundle
import com.davidluna.architectcoders2024.build_logic.libs.androidLibrary
import com.davidluna.architectcoders2024.build_logic.libs.arrowCore
import com.davidluna.architectcoders2024.build_logic.libs.javaxInject
import com.davidluna.architectcoders2024.build_logic.libs.kotlinAndroid
import com.davidluna.architectcoders2024.build_logic.libs.kotlinCoroutinesCore
import com.davidluna.architectcoders2024.build_logic.libs.kotlinSerialization
import com.davidluna.architectcoders2024.build_logic.libs.kotlinxSerializationJson
import com.davidluna.architectcoders2024.build_logic.libs.libs
import com.davidluna.architectcoders2024.build_logic.libs.retrofit
import com.davidluna.architectcoders2024.build_logic.utils.android
import com.davidluna.architectcoders2024.build_logic.utils.java
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FrameworkModuleConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugins()
            setProjectConfig()
            dependencies()
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            alias(libs.androidLibrary)
            alias(libs.kotlinAndroid)
            alias(libs.kotlinSerialization)
        }
    }

    private fun Project.setProjectConfig() {
        android {

            compileSdk = Constants.COMPILE_SDK
            defaultConfig {
                minSdk = Constants.MIN_SDK
                testInstrumentationRunner = Constants.TEST_INSTRUMENTATION_RUNNER
                consumerProguardFiles("consumer-rules.pro")
            }

            buildTypes {
                release {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }


            compileOptions {
                sourceCompatibility = Constants.JAVA_VERSION
                targetCompatibility = Constants.JAVA_VERSION
            }
        }

        java {
            sourceCompatibility = Constants.JAVA_VERSION
            targetCompatibility = Constants.JAVA_VERSION
        }
    }

    private fun Project.dependencies() {
        dependencies {
            implementation(libs.kotlinxSerializationJson)
            implementation(libs.retrofit)
            implementation(libs.arrowCore)
            implementation(libs.kotlinCoroutinesCore)
            implementation(libs.javaxInject)
            unitTestingBundle()

        }
    }

}