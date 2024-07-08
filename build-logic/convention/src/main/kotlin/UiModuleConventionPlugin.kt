import com.android.build.api.dsl.LibraryExtension
import com.davidluna.architectcoders2024.build_logic.constants.Constants
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.alias
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.androidTestImplementation
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.debugImplementation
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.implementation
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.kapt
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.testImplementation
import com.davidluna.architectcoders2024.build_logic.libs.androidLibrary
import com.davidluna.architectcoders2024.build_logic.libs.arrowCore
import com.davidluna.architectcoders2024.build_logic.libs.composeAnimation
import com.davidluna.architectcoders2024.build_logic.libs.composeBom
import com.davidluna.architectcoders2024.build_logic.libs.composeCompiler
import com.davidluna.architectcoders2024.build_logic.libs.composeMaterial3
import com.davidluna.architectcoders2024.build_logic.libs.composeNavigation
import com.davidluna.architectcoders2024.build_logic.libs.composeUi
import com.davidluna.architectcoders2024.build_logic.libs.composeUiGraphics
import com.davidluna.architectcoders2024.build_logic.libs.composeUiTooling
import com.davidluna.architectcoders2024.build_logic.libs.composeUiToolingPreview
import com.davidluna.architectcoders2024.build_logic.libs.espressoCore
import com.davidluna.architectcoders2024.build_logic.libs.extJunit
import com.davidluna.architectcoders2024.build_logic.libs.hiltAndroid
import com.davidluna.architectcoders2024.build_logic.libs.hiltCompiler
import com.davidluna.architectcoders2024.build_logic.libs.hiltNavigationCompose
import com.davidluna.architectcoders2024.build_logic.libs.hiltPlugin
import com.davidluna.architectcoders2024.build_logic.libs.iconsExtended
import com.davidluna.architectcoders2024.build_logic.libs.junit
import com.davidluna.architectcoders2024.build_logic.libs.kapt
import com.davidluna.architectcoders2024.build_logic.libs.kotlinAndroid
import com.davidluna.architectcoders2024.build_logic.libs.libs
import com.davidluna.architectcoders2024.build_logic.libs.runtimeTracing
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class UiModuleConventionPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        applyPlugins()
        setProjectConfig()
        dependencies()
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            alias(libs.androidLibrary)
            alias(libs.kotlinAndroid)
            alias(libs.kapt)
            alias(libs.hiltPlugin)
            alias(libs.composeCompiler)
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
                compose = true
            }

            compileOptions {
                sourceCompatibility = Constants.JAVA_VERSION
                targetCompatibility = Constants.JAVA_VERSION
            }

        }
    }

    private fun Project.dependencies() {
        dependencies {
            composeUiBundle()
            implementation(libs.arrowCore)
            implementation(libs.hiltNavigationCompose)
            implementation(libs.hiltAndroid)
            kapt(libs.hiltCompiler)
            testImplementation(libs.junit)
            androidTestImplementation(libs.extJunit)
            androidTestImplementation(libs.espressoCore)
        }
    }


    private fun Project.composeUiBundle() {
        dependencies {
            implementation(platform(libs.composeBom))
            implementation(libs.composeUi)
            implementation(libs.composeUiGraphics)
            implementation(libs.composeUiToolingPreview)
            implementation(libs.composeMaterial3)
            implementation(libs.iconsExtended)
            implementation(libs.composeNavigation)
            implementation(libs.composeAnimation)
            debugImplementation(libs.runtimeTracing)
            debugImplementation(libs.composeUiTooling)
        }
    }

    private fun Project.android(action: LibraryExtension.() -> Unit) {
        action(extensions.getByType(LibraryExtension::class.java))
    }

}