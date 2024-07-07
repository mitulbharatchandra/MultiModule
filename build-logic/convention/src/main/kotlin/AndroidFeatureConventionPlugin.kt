import com.app.multimodule.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("multimodule.android.library")
                apply("multimodule.android.hilt")
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("coil.compose").get())
                add("implementation", libs.findLibrary("androidx.tracing.ktx").get())

                add("androidTestImplementation", libs.findLibrary("androidx.lifecycle.runtimeTesting").get())
            }
        }
    }
}