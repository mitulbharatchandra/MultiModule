package com.app.multimodule.core.data.repository

import com.app.multimodule.core.resource_assets.MediaDataSource
import com.app.multimodule.core.resource_assets.model.Media
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaDataSource: MediaDataSource
) : MediaRepository {

    override suspend fun getMedia(): Media {
        return mediaDataSource.getMedia()
    }
}