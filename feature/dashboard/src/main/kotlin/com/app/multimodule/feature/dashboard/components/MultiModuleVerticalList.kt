package com.app.multimodule.feature.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.app.multimodule.core.common.model.ItemVM
import com.app.multimodule.core.common.ui.component.ListCard
import com.app.multimodule.feature.dashboard.MultiModuleList

@Composable
fun MultiModuleVerticalList(
    mediaItems: List<ItemVM>,
    onItemClick: (itemVM: ItemVM) -> Unit,
    listState: LazyListState = LazyListState()
) {
    val windowWidthSize =
        currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    val imageHeight = when (windowWidthSize) {
        WindowWidthSizeClass.EXPANDED -> 200.dp
        else -> 250.dp
    }
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        mediaItems.forEach { mediaItem ->
            item(
                key = mediaItem.id
            ) {
                ListCard(
                    itemContent = mediaItem,
                    onItemClick = onItemClick,
                    modifier = Modifier
                        .height(imageHeight)
                        .fillMaxWidth()
                )
            }
            mediaItem.content?.let { moreContent ->
                item {
                    MultiModuleList(
                        content = moreContent,
                        onItemClick = onItemClick
                    )
                }
            }
        }
    }
}