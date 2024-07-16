import com.android.build.api.dsl.ApplicationExtension
import com.davidluna.architectcoders2024.build_logic.constants.Constants.COMPILE_SDK
import com.davidluna.architectcoders2024.build_logic.constants.Constants.JAVA_VERSION
import com.davidluna.architectcoders2024.build_logic.constants.Constants.MIN_SDK
import com.davidluna.architectcoders2024.build_logic.constants.Constants.NAMESPACE
import com.davidluna.architectcoders2024.build_logic.constants.Constants.TARGET_SDK
import com.davidluna.architectcoders2024.build_logic.constants.Constants.TEST_INSTRUMENTATION_RUNNER
import com.davidluna.architectcoders2024.build_logic.constants.Constants.VERSION_CODE
import com.davidluna.architectcoders2024.build_logic.constants.Constants.VERSION_NAME
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.alias
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.implementation
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.kapt
import com.davidluna.architectcoders2024.build_logic.libs.androidApplication
import com.davidluna.architectcoders2024.build_logic.libs.composeActivity
import com.davidluna.architectcoders2024.build_logic.libs.composeCompiler
import com.davidluna.architectcoders2024.build_logic.libs.hiltAndroid
import com.davidluna.architectcoders2024.build_logic.libs.hiltCompiler
import com.davidluna.architectcoders2024.build_logic.libs.hiltPlugin
import com.davidluna.architectcoders2024.build_logic.libs.kapt
import com.davidluna.architectcoders2024.build_logic.libs.kotlinAndroid
import com.davidluna.architectcoders2024.build_logic.libs.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        applyPlugins()
        setProjectConfig()
        setDependencies()
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            alias(libs.androidApplication)
            alias(libs.kotlinAndroid)
            alias(libs.kapt)
            alias(libs.hiltPlugin)
            alias(libs.composeCompiler)
        }
    }

    private fun Project.setProjectConfig() {
        android {
            namespace = NAMESPACE
            compileSdk = COMPILE_SDK

            defaultConfig {
                applicationId = NAMESPACE
                minSdk = MIN_SDK
                targetSdk = TARGET_SDK
                versionCode = VERSION_CODE
                versionName = VERSION_NAME
                testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
                vectorDrawables {
                    useSupportLibrary = true
                }
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
                sourceCompatibility = JAVA_VERSION
                targetCompatibility = JAVA_VERSION
            }
            buildFeatures {
                buildConfig = true
                compose = true
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

        }

        java {
            sourceCompatibility = JAVA_VERSION
            targetCompatibility = JAVA_VERSION
        }
    }

    private fun Project.setDependencies() {
        dependencies {
            implementation(libs.composeActivity)
            implementation(libs.hiltAndroid)
            kapt(libs.hiltCompiler)
        }
    }


    private fun Project.android(action: ApplicationExtension.() -> Unit) {
        action(extensions.getByType(ApplicationExtension::class.java))
    }

    private fun Project.java(action: JavaPluginExtension.() -> Unit) {
        action(extensions.getByType(JavaPluginExtension::class.java))
    }

}