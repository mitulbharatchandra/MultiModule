package com.app.multimodule.core.common.model

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState

data class MediaVM(
    val displayStyle: DisplayStyle,
    val items: List<ItemVM>,
    val listState: LazyListState = LazyListState()
)

data class ContentVM(
    val displayStyle: DisplayStyle,
    val items: List<ItemVM>,
    val listState: LazyListState = LazyListState(),
    val gridState: LazyGridState = LazyGridState()
)

data class ItemVM(
    val content: ContentVM? = null,
    val id: String,
    val subtitle: String? = null,
    val thumbnail: String? = null,
    val title: String
)

fun Media.toMediaVM(): MediaVM =
    MediaVM(
        displayStyle = displayStyle,
        items = items.mapToItemVM()
    )

fun List<Item>.mapToItemVM(): List<ItemVM> =
    map { item ->
        ItemVM(
            content = item.content?.let {
                ContentVM(
                    displayStyle = it.displayStyle,
                    items = it.items.mapToItemVM()
                )
            },
            id = item.id,
            subtitle = item.subtitle,
            thumbnail = item.thumbnail,
            title = item.title
        )
    }