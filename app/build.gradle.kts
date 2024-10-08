plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.apiresponse"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.apiresponse"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
buildFeatures{
viewBinding = true
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.glide)
    kapt (libs.compiler)
    implementation (libs.logging.interceptor)



    implementation (libs.androidx.lifecycle.extensions)
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.activity.ktx)
    implementation (libs.androidx.fragment.ktx)
    implementation (libs.androidx.room.ktx)
  //noinspection KaptUsageInsteadOfKsp
  //kapt (libs.androidx.room.compiler)


    implementation (libs.androidx.room.runtime)
    annotationProcessor (libs.androidx.room.compiler)

    // To use Kotlin annotation processing tool (kapt)
    kapt (libs.androidx.room.compiler)
 //  kapt(libs.androidx.room.compiler.v250)
    implementation (libs.kotlinx.coroutines.android)
}