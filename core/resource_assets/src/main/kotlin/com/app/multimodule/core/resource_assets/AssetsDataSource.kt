package com.app.multimodule.core.resource_assets

import com.app.multimodule.core.resource_assets.model.Media

interface AssetsDataSource {
    suspend fun getMedia(): Media
}