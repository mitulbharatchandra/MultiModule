@file:OptIn(ExperimentalSerializationApi::class)

package com.app.multimodule.core.resource_assets.manager

import com.app.multimodule.core.common.network.Dispatcher
import com.app.multimodule.core.common.network.MultiModuleDispatchers
import com.app.multimodule.core.resource_assets.AssetManager
import com.app.multimodule.core.resource_assets.MediaDataSource
import com.app.multimodule.core.resource_assets.model.Media
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
): MediaDataSource {

    override suspend fun getMedia(): Media =
        withContext(ioDispatcher) {
            assetManager.open("media.json").use(networkJson::decodeFromStream)
        }

}