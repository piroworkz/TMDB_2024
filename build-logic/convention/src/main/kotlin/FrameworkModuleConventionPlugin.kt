import com.android.build.api.dsl.LibraryExtension
import com.davidluna.architectcoders2024.build_logic.constants.Constants
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.androidTestImplementation
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.implementation
import com.davidluna.architectcoders2024.build_logic.dependency_utilities.testImplementation
import com.davidluna.architectcoders2024.build_logic.libs.androidLibrary
import com.davidluna.architectcoders2024.build_logic.libs.arrowCore
import com.davidluna.architectcoders2024.build_logic.libs.espressoCore
import com.davidluna.architectcoders2024.build_logic.libs.extJunit
import com.davidluna.architectcoders2024.build_logic.libs.javaxInject
import com.davidluna.architectcoders2024.build_logic.libs.junit
import com.davidluna.architectcoders2024.build_logic.libs.kotlinAndroid
import com.davidluna.architectcoders2024.build_logic.libs.kotlinCoroutinesCore
import com.davidluna.architectcoders2024.build_logic.libs.kotlinSerialization
import com.davidluna.architectcoders2024.build_logic.libs.kotlinxSerializationJson
import com.davidluna.architectcoders2024.build_logic.libs.libs
import com.davidluna.architectcoders2024.build_logic.libs.retrofit
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
        project.apply {
            plugin(libs.androidLibrary.get().pluginId)
            plugin(libs.kotlinAndroid.get().pluginId)
            plugin(libs.kotlinSerialization.get().pluginId)
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
    }

    private fun Project.dependencies() {
        dependencies {
            implementation(libs.kotlinxSerializationJson)
            implementation(libs.retrofit)
            implementation(libs.arrowCore)
            implementation(libs.kotlinCoroutinesCore)
            implementation(libs.javaxInject)

            testImplementation(libs.junit)
            androidTestImplementation(libs.extJunit)
            androidTestImplementation(libs.espressoCore)
        }
    }

    private fun Project.android(action: LibraryExtension.() -> Unit) {
        action(extensions.getByType(LibraryExtension::class.java))
    }

}