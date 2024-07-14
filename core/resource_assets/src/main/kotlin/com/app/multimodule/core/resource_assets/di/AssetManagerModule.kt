package com.app.multimodule.core.resource_assets.di

import android.content.Context
import com.app.multimodule.core.resource_assets.AssetManager
import com.app.multimodule.core.resource_assets.manager.AssetsManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object AssetManagerModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesAssetManager(
        @ApplicationContext context: Context,
    ): AssetManager = AssetsManagerImpl(context)

}