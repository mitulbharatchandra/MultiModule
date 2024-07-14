package com.app.multimodule.core.resource_assets.manager

import com.app.multimodule.core.common.model.DisplayStyle
import com.app.multimodule.core.resource_assets.JvmUnitTestFakeAssetManager
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AssetsDataSourceImplTest {

    private lateinit var subject: AssetsDataSourceImpl

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        subject = AssetsDataSourceImpl(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assetManager = JvmUnitTestFakeAssetManager
        )
    }

    @Test
    fun testDeserializationOfMedia() = runTest(testDispatcher) {
        val media = subject.getMedia("media.json")
        val titleHome = media.items.first().title
        val idHome = media.items.first().id
        val homeContentFirstItemId = media.items.first().content?.items?.first()?.id
        val homeContentInnerItemId =
            media.items.first().content?.items?.get(6)?.content?.items?.first()?.id
        val idLibrary = media.items.last().id
        val libraryContentCount = media.items.last().content?.items?.count()
        assertEquals("Home", titleHome)
        assertEquals("home", idHome)
        assertEquals(4, media.items.count())
        assertEquals("track1", homeContentFirstItemId)
        assertEquals("track9", homeContentInnerItemId)
        assertEquals("library", idLibrary)
        assertEquals(0, libraryContentCount)
    }

    @Test
    fun testDeserializationOfEnum() = runTest(testDispatcher) {
        val media = subject.getMedia("media.json")
        val parentDisplayStyle = media.displayStyle
        val homeContentDisplayStyle = media.items.first().content?.displayStyle
        val homeContentInnerItemsDisplayStyle =
            media.items.first().content?.items?.get(6)?.content?.displayStyle
        assertEquals(DisplayStyle.GRID, parentDisplayStyle)
        assertEquals(DisplayStyle.GRID, homeContentDisplayStyle)
        assertEquals(DisplayStyle.LIST, homeContentInnerItemsDisplayStyle)
    }
}