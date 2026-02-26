plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.interrapidisimo.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.interrapidisimo.app"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "100"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}
kotlin {
    jvmToolchain(21)
}
dependencies {

    // ---------- CORE ----------
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.recyclerview)

    // ---------- COMPOSE ----------
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.lifecycle)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // ---------- NETWORK ----------
    implementation(libs.bundles.network)

    // ---------- COROUTINES ----------
    implementation(libs.kotlinx.coroutines.android)

    // ---------- DATABASE ----------
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // ---------- MOSHI ----------
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.codegen)

    // ---------- HILT ----------
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // ---------- TESTING ----------
    testImplementation(libs.bundles.testing)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.compose.testing)
}