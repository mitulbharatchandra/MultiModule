package com.app.multimodule.core.resource_assets.di

import com.app.multimodule.core.resource_assets.AssetsDataSource
import com.app.multimodule.core.resource_assets.manager.AssetsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindsAssetDataSource(
        assetsDataSource: AssetsDataSourceImpl
    ): AssetsDataSource
}