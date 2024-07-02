package com.app.multimodule.core.data.repository

import com.app.multimodule.core.resource_assets.model.DisplayStyle
import com.app.multimodule.core.resource_assets.model.Media

class TestMediaRepository : MediaRepository {

    private var result: Result<Media> = Result.success(Media(
        displayStyle = DisplayStyle.GRID,
        items = emptyList()
    ))

    fun setReturnResult(result: Result<Media>) {
        this.result = result
    }

    override suspend fun getMedia(): Result<Media> = result

}