package com.app.multimodule.core.resource_assets

import com.app.multimodule.core.common.model.Media

interface AssetsDataSource {
    suspend fun getMedia(fileName: String): Media
}