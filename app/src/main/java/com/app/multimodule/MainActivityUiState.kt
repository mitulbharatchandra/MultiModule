package com.app.multimodule

import com.app.multimodule.core.common.model.ItemVM
import com.app.multimodule.core.common.model.MediaVM

data class MainActivityUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val media: MediaVM? = null,
    val selectedItem: ItemVM? = null
)