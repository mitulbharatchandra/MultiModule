package com.app.multimodule.feature.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.app.multimodule.core.common.model.ItemVM
import com.app.multimodule.core.common.ui.component.ListCard
import com.app.multimodule.feature.dashboard.MultiModuleList

@Composable
fun MultiModuleHorizontalGrid(
    itemsList: List<ItemVM>,
    onItemClick: (itemVM: ItemVM) -> Unit,
    gridState: LazyGridState = LazyGridState()
) {

    val windowWidthSize =
        currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    val imageHeight = when (windowWidthSize) {
        WindowWidthSizeClass.EXPANDED -> 200.dp
        else -> 250.dp
    }

    val imageWith = when (windowWidthSize) {
        WindowWidthSizeClass.EXPANDED -> 150.dp
        else -> 200.dp
    }

    LazyHorizontalGrid(
        state = gridState,
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .height(440.dp)
            .fillMaxWidth()
    ) {
        itemsList.forEach { media ->
            item(
                key = media.id
            ) {
                ListCard(
                    itemContent = media,
                    onItemClick = onItemClick,
                    modifier = Modifier
                        .width(imageWith)
                        .height(imageHeight)
                        .fillMaxWidth()
                )
            }
            media.content?.let { moreContent ->
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