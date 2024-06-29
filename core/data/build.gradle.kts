plugins {
    alias(libs.plugins.multimodule.android.library)
    alias(libs.plugins.multimodule.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.app.multimodule.core.data"
}

dependencies {
    api(project(":core:common"))
    api(project(":core:resource_assets"))

    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.kotlinx.coroutines.test)
}