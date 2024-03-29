plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.lofinif.sharedtest"
    compileSdk = 34
    val UI_SYSTEM_VIEW = "view"
    val UI_SYSTEM_COMPOSE = "compose"

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "UI_SYSTEM_VIEW", "\"$UI_SYSTEM_VIEW\"")
        buildConfigField("String", "UI_SYSTEM_COMPOSE", "\"$UI_SYSTEM_COMPOSE\"")
    }

    flavorDimensions += "ui_type"

    productFlavors {
        create("view") {
            dimension = "ui_type"
        }
        create("compose") {
            dimension = "ui_type"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":app"))
    implementation(project(":app"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}