package com.app.multimodule.core.resource_assets

import androidx.annotation.VisibleForTesting
import java.io.File
import java.io.InputStream
import java.util.Properties

/**
 * This class helps with loading Android `/assets` files, especially when running JVM unit tests.
 * It must remain on the root package for an easier [Class.getResource] with relative paths.
 * @see <a href="https://developer.android.com/reference/tools/gradle-api/7.3/com/android/build/api/dsl/UnitTestOptions">UnitTestOptions</a>
 */
@VisibleForTesting
internal object JvmUnitTestFakeAssetManager : AssetManager {
    override fun open(fileName: String): InputStream = File("../../core/resource_assets/src/main/assets/", fileName).inputStream()
}
