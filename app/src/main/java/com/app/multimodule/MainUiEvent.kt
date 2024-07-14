package com.app.multimodule

import com.app.multimodule.core.common.model.ItemVM

sealed class MainUiEvent {
    data class OnItemClicked(val itemVM: ItemVM) : MainUiEvent()
}