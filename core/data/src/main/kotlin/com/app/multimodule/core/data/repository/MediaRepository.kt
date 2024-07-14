package com.app.multimodule.core.data.repository

import com.app.multimodule.core.common.model.Media

interface MediaRepository {
    suspend fun getMedia(): Result<Media>
}