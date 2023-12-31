plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
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
    implementation("com.google.android.material:material:1.11.0")
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
    implementation ("android.arch.lifecycle:extensions:1.1.1")

    //Dynamic Sizes
    implementation("com.github.MrNouri:DynamicSizes:1.0")

    //OTP View
    implementation("com.github.aabhasr1:OtpView:v1.1.2")

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    //Glide Image Library
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    //Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
}