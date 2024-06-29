@file:Suppress("UnstableApiUsage")

plugins {
    alias(libs.plugins.multimodule.android.library)
    alias(libs.plugins.multimodule.android.hilt)
    id("kotlinx-serialization")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.app.multimodule.core.resource_assets"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(project(":core:common"))
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.kotlinx.coroutines.test)
}