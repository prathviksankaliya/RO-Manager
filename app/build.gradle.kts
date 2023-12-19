plugins {
    id("com.android.application")
}

android {
    namespace = "com.itcraftsolution.romanager"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.itcraftsolution.romanager"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures{
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Room
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    //MVVM

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata:2.6.2")

    //Dynamic Sizes
    implementation("com.github.MrNouri:DynamicSizes:1.0")
}