package com.app.multimodule

import com.app.multimodule.core.common.model.DisplayStyle
import com.app.multimodule.core.common.model.Item
import com.app.multimodule.core.common.model.Media
import com.app.multimodule.core.common.model.toMediaVM
import com.app.multimodule.core.data.repository.TestMediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainActivityViewModelTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private lateinit var testMediaRepository: TestMediaRepository
    private lateinit var subject: MainActivityViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `requestMedia success updates uiState with media`() = runTest {
        val result = Result.success(
            Media(
                displayStyle = DisplayStyle.GRID,
                items = listOf(
                    Item(
                        id = "1",
                        title = "test",
                        subtitle = "subtitle test"
                    )
                )
            )
        )
        testMediaRepository = TestMediaRepository()
        testMediaRepository.setReturnResult(result)
        subject = MainActivityViewModel(mediaRepository = testMediaRepository)
        backgroundScope.launch(UnconfinedTestDispatcher()) { subject.uiState.collect() }

        assertEquals(result.getOrNull()?.toMediaVM()?.items, subject.uiState.value.media?.items)
        assertEquals(false, subject.uiState.value.isLoading)
        assertEquals(null, subject.uiState.value.errorMessage)
    }

    @Test
    fun `requestMedia success updates uiState with failure message`() = runTest {
        val errorMessage = "Error fetching media"
        val result = Result.failure<Media>(Exception(errorMessage))
        testMediaRepository = TestMediaRepository()
        testMediaRepository.setReturnResult(result)
        subject = MainActivityViewModel(mediaRepository = testMediaRepository)
        backgroundScope.launch(UnconfinedTestDispatcher()) { subject.uiState.collect() }

        assertEquals(null, subject.uiState.value.media)
        assertEquals(false, subject.uiState.value.isLoading)
        assertEquals(errorMessage, subject.uiState.value.errorMessage)
    }

}