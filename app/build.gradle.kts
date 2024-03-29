
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id ("kotlinx-serialization")
    id ("kotlin-parcelize")
}

android {
    val UI_SYSTEM_VIEW = "view"
    val UI_SYSTEM_COMPOSE = "compose"
    namespace = "com.lofinif.gosporttestapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lofinif.gosporttestapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "UI_SYSTEM_VIEW", "\"$UI_SYSTEM_VIEW\"")
        buildConfigField("String", "UI_SYSTEM_COMPOSE", "\"$UI_SYSTEM_COMPOSE\"")

    }

    flavorDimensions += "ui_type"

    productFlavors {
        create("view") {
            dimension = "ui_type"
            applicationIdSuffix = ".$UI_SYSTEM_VIEW"
            versionNameSuffix = "-$UI_SYSTEM_VIEW"

        }
        create("compose") {
            dimension = "ui_type"
            applicationIdSuffix = ".$UI_SYSTEM_COMPOSE"
            versionNameSuffix = "-$UI_SYSTEM_COMPOSE"

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
        viewBinding = true
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
}

dependencies {

    implementation(libs.composeMaterial3)
    implementation(libs.constraintlayoutCompose)
    implementation(libs.activityCompose)
    implementation(libs.viewmodelCompose)
    implementation(libs.composeUiTooling)
    implementation(libs.composeRuntimeLiveData)
    implementation(libs.shimmerCompose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    testImplementation(project(":sharedTest"))
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.glideCompose)
    implementation(libs.hiltNavigationCompose)
    implementation(libs.navigation.compose)

    testImplementation(libs.coroutines.test)
    implementation(libs.truth)
    testImplementation(libs.mockk)
    implementation(libs.moshi)
    testImplementation(project(":app"))
    implementation(libs.moshiConevecter)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.shimmer)
    implementation(libs.glide)
    debugImplementation(libs.androidx.ui.tooling)
    kapt(libs.roomCompile)
    implementation(libs.room)
    implementation(libs.retrofit)
    implementation(libs.retrofit2.serialization.converter)
    implementation(libs.okHttp3)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.daggerHilt)
    kapt(libs.daggerHiltCompiler)
    implementation(libs.androidx.recyclerview)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
kapt {
    correctErrorTypes = true
}