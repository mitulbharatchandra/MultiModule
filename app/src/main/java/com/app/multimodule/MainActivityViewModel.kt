package com.app.multimodule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.multimodule.core.data.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val mediaRepository: MediaRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityUiState())
    val uiState = _uiState.asStateFlow()

    init {
        requestMedia()
    }

    private fun requestMedia() {
        viewModelScope.launch {
            mediaRepository.getMedia()
                .onSuccess { media ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            media = media
                        )
                    }
                }
                .onFailure { error ->
                    error.message?.let { message ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = message
                            )
                        }
                    }
                    _uiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun onEvent(event: MainUiEvent) {
        when(event) {
            is MainUiEvent.OnNavigate -> _uiState.update {
                it.copy(selectedItemIndex = event.index)
            }
        }
    }
}