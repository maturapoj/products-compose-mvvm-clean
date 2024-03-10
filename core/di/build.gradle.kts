plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.most.core.di"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
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
    flavorDimensions.add("environment")
    productFlavors {
        create("develop") {
            dimension = "environment"
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://659f86b15023b02bfe89c737.mockapi.io/api/v1/\""
            )
        }
        create("stage") {
            dimension = "environment"
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://659f86b15023b02bfe89c737.mockapi.io/api/v1/\""
            )
        }
    }
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:common"))

    // DI Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compat)
    implementation(libs.koin.workmanager)
    implementation(libs.koin.navigation)
    implementation(libs.koin.compose)

    // retrofit and okhttp
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // chucker
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)


}