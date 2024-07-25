plugins {
    alias(libs.plugins.architectCodersAndroidApplication)
}

android{
    signingConfigs {
        create("release") {
            keyAlias = System.getenv("MY_KEY_ALIAS") ?: project.findProperty("MY_KEY_ALIAS") as String
            keyPassword = System.getenv("MY_KEY_PASSWORD") ?: project.findProperty("MY_KEY_PASSWORD") as String
            storeFile = file(System.getenv("MY_STORE_FILE") ?: project.findProperty("MY_STORE_FILE") as String)
            storePassword = System.getenv("MY_STORE_PASSWORD") ?: project.findProperty("MY_STORE_PASSWORD") as String
        }
    }

    buildTypes{
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(projects.navigation)
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(projects.main)
}