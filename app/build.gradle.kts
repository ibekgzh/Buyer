plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version "1.8.21-1.0.11"
}

android {
    namespace = "com.example.buyerapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.buyerapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"http://77.240.38.178/api/\"")
            buildConfigField("String", "DB_NAME", "\"AppDatabase\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val hilt_version = "2.46.1"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Material for BottomNavigation
    implementation("androidx.compose.material:material:1.4.2")

    //HorizontalPager
    implementation("androidx.compose.foundation:foundation:1.4.3")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Chucker Interceptor
    implementation("com.github.chuckerteam.chucker:library:3.5.2")

    // Coil - for loading image
    implementation("io.coil-kt:coil-compose-base:2.3.0")
    // Hilt DI
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.5.1")
    implementation("io.github.raamcosta.compose-destinations:core:1.9.42-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.9.42-beta")
    implementation("io.github.raamcosta.compose-destinations:animations-core:1.9.42-beta")

    //DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("androidx.room:room-ktx:2.4.2")
    ksp("androidx.room:room-compiler:2.4.2")

    // QR Render
    implementation("com.lightspark:compose-qr-code:1.0.1")

    val cameraxVersion = "1.2.3"
    implementation("androidx.camera:camera-core:${cameraxVersion}")
    implementation("androidx.camera:camera-camera2:${cameraxVersion}")
    implementation("androidx.camera:camera-lifecycle:${cameraxVersion}")
    implementation("androidx.camera:camera-view:1.3.0-alpha07")

    //Barcode
    implementation("com.google.mlkit:barcode-scanning:17.1.0")

    //Camera Permission
    implementation("com.google.accompanist:accompanist-permissions:0.19.0")

}