plugins {
    alias(libs.plugins.multimodule.android.library)
    alias(libs.plugins.multimodule.android.hilt)
    alias(libs.plugins.multimodule.android.library.compose)
    alias(libs.plugins.multimodule.android.feature)
    id("kotlinx-serialization")
}

android {
    namespace = "com.app.multimodule.core.common"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.kotlinx.coroutines.test)
}