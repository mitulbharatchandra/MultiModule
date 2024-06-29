plugins {
    alias(libs.plugins.multimodule.android.library)
    alias(libs.plugins.multimodule.android.hilt)
}

android {
    namespace = "com.app.multimodule.core.data"
}

dependencies {
    api(project(":core:common"))
    api(project(":core:resource_assets"))
    testImplementation(libs.kotlinx.coroutines.test)
}