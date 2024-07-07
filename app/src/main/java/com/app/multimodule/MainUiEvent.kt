package com.app.multimodule

sealed class MainUiEvent {
    data class OnNavigate(val index: Int) : MainUiEvent()
}