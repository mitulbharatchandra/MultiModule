plugins {
    alias(libs.plugins.multimodule.android.library)
    alias(libs.plugins.multimodule.android.hilt)
}

android {
    namespace = "com.app.multimodule.core.common"
}

dependencies {
    testImplementation(libs.kotlinx.coroutines.test)
}