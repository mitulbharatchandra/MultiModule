package com.app.multimodule.core.resource_assets.manager

import android.content.Context
import com.app.multimodule.core.resource_assets.AssetManager
import java.io.InputStream
import javax.inject.Inject

class AssetsManagerImpl(private val context: Context) : AssetManager {

    override fun open(fileName: String): InputStream {
        return context.assets.open(fileName)
    }

}