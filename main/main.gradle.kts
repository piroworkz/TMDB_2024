import com.davidluna.architectcoders2024.build_logic.constants.Constants

plugins {
    alias(libs.plugins.uiModuleConventionPlugin)
}

android {
    namespace = Constants.NAMESPACE.plus(".main")

    defaultConfig {
        @Suppress("UnstableApiUsage")
        externalNativeBuild {
            ndkBuild {
                arguments(
                    "${Constants.API_KEY}=${project.property(Constants.API_KEY)}",
                    "${Constants.BASE_URL}=${project.property(Constants.BASE_URL)}"
                )
            }
        }

        ndk {
            abiFilters.addAll(Constants.ABI_FILTERS)
        }
    }

    externalNativeBuild {
        ndkBuild {
            path = file("src/main/jni/Android.mk")

        }
    }
}

dependencies {
    implementation(projects.navigation)
    implementation(projects.feature.splash)
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreFramework)
    implementation(projects.feature.auth.authUi)
    implementation(projects.feature.auth.authDomain)
    implementation(projects.feature.auth.authFramework)
    implementation(projects.feature.media.mediaUi)
    implementation(projects.feature.media.mediaDomain)
    implementation(projects.feature.media.mediaFramework)
    implementation(projects.feature.videos.videosUi)
    implementation(projects.feature.videos.videosDomain)
    implementation(projects.feature.videos.videosFramework)

    implementation(libs.composeActivity)
    implementation(libs.coilCompose)
    implementation(libs.navigation)

    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)
    implementation(libs.retrofit)
    implementation(libs.okhttpClient)
    implementation(libs.okhttpLoggingInterceptor)
    implementation(libs.kotlinxSerializationJson)
    implementation(libs.kotlinConverter)

    testImplementation(projects.testShared)
}