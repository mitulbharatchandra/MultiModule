package com.app.multimodule.core.resource_assets

import java.io.InputStream

fun interface AssetManager {
    fun open(fileName: String): InputStream
}