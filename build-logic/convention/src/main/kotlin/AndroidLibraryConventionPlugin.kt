
import com.davidluna.architectcoders2024.build_logic.constants.Constants
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.alias
import com.davidluna.architectcoders2024.build_logic.libs.androidLibrary
import com.davidluna.architectcoders2024.build_logic.libs.kotlinAndroid
import com.davidluna.architectcoders2024.build_logic.libs.libs
import com.davidluna.architectcoders2024.build_logic.utils.android
import com.davidluna.architectcoders2024.build_logic.utils.java
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        applyPlugins()
        setProjectConfig()
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            alias(libs.androidLibrary)
            alias(libs.kotlinAndroid)
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

            buildFeatures {
                buildConfig = true
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

}