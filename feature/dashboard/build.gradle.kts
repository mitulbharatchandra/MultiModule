plugins {
    alias(libs.plugins.multimodule.android.feature)
    alias(libs.plugins.multimodule.android.library.compose)
}

android {
    namespace = "com.app.multimodule.feature"
}

dependencies {
    implementation(project(":core:data"))
}