package com.app.multimodule.core.data.repository

import com.app.multimodule.core.resource_assets.model.Media

interface MediaRepository {
    suspend fun getMedia(): Result<Media>
}