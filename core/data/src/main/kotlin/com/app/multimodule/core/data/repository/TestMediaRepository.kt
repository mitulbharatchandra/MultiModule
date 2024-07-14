package com.app.multimodule.core.data.repository

import com.app.multimodule.core.common.model.DisplayStyle
import com.app.multimodule.core.common.model.Media

class TestMediaRepository : MediaRepository {

    private var result: Result<Media> = Result.success(
        Media(
            displayStyle = DisplayStyle.VERTICAL_LIST,
            items = emptyList()
        )
    )

    fun setReturnResult(result: Result<Media>) {
        this.result = result
    }

    override suspend fun getMedia(): Result<Media> = result

}