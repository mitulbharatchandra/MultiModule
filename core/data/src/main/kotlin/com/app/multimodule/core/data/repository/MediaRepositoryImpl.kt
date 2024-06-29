package com.app.multimodule.core.data.repository

import com.app.multimodule.core.resource_assets.AssetsDataSource
import com.app.multimodule.core.resource_assets.model.Media
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val assetsDataSource: AssetsDataSource
) : MediaRepository {

    override suspend fun getMedia(): Media {
        return assetsDataSource.getMedia()
    }
}