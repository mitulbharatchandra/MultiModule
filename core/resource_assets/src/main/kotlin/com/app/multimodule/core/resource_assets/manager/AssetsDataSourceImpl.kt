@file:OptIn(ExperimentalSerializationApi::class)

package com.app.multimodule.core.resource_assets.manager

import com.app.multimodule.core.common.model.Media
import com.app.multimodule.core.common.network.Dispatcher
import com.app.multimodule.core.common.network.MultiModuleDispatchers
import com.app.multimodule.core.resource_assets.AssetManager
import com.app.multimodule.core.resource_assets.AssetsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class AssetsDataSourceImpl @Inject constructor(
    @Dispatcher(MultiModuleDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val assetManager: AssetManager,
    private val networkJson: Json
) : AssetsDataSource {

    override suspend fun getMedia(fileName: String): Media =
        withContext(ioDispatcher) {
            assetManager.open(fileName).use(networkJson::decodeFromStream)
        }

}