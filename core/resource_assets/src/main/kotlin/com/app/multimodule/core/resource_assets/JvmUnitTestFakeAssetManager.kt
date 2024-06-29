package com.app.multimodule.core.resource_assets

import androidx.annotation.VisibleForTesting
import java.io.File
import java.io.InputStream

@VisibleForTesting
internal object JvmUnitTestFakeAssetManager : AssetManager {
    override fun open(fileName: String): InputStream = File("../../core/resource_assets/src/main/assets/", fileName).inputStream()
}
