package com.app.multimodule.core.data.di

import com.app.multimodule.core.data.repository.MediaRepository
import com.app.multimodule.core.data.repository.MediaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsMediaRepository(
        mediaRepository: MediaRepositoryImpl
    ): MediaRepository
}