plugins {
    alias(libs.plugins.multimodule.android.feature)
    alias(libs.plugins.multimodule.android.library.compose)
}

android {
    namespace = "com.app.multimodule.feature.dashboard"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":feature:itemdetails"))
    implementation(project(":core:common"))

    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
}