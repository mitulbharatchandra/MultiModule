package com.app.multimodule

import com.app.multimodule.core.resource_assets.model.Media

data class MainActivityUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val media: Media? = null,
    val selectedItemIndex: Int = 0
)