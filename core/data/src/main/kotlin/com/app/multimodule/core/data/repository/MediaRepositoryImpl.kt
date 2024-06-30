package com.app.multimodule.core.data.repository

import com.app.multimodule.core.resource_assets.AssetsDataSource
import com.app.multimodule.core.resource_assets.model.Media
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class MediaRepositoryImpl @Inject constructor(
    private val assetsDataSource: AssetsDataSource
) : MediaRepository {

    override suspend fun getMedia(): Result<Media> {
        return try {
            Result.success(assetsDataSource.getMedia("media.json"))
        } catch (e: Exception) {
            if(e is CancellationException) throw e
            e.printStackTrace()
            Result.failure(exception = e)
        }
    }
}