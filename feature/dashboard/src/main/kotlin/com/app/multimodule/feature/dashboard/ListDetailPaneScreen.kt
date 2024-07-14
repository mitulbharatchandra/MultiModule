@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.app.multimodule.feature.dashboard

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.app.multimodule.core.common.model.ContentVM
import com.app.multimodule.core.common.model.DisplayStyle.HORIZONTAL_GRID
import com.app.multimodule.core.common.model.DisplayStyle.VERTICAL_LIST
import com.app.multimodule.core.common.model.ItemVM
import com.app.multimodule.feature.dashboard.components.MultiModuleHorizontalGrid
import com.app.multimodule.feature.dashboard.components.MultiModuleVerticalList
import com.app.multimodule.feature.itemdetails.ItemDetailsDetailsScreen

@Composable
fun ListDetailPaneScreen(
    content: ContentVM,
    selectedItem: ItemVM?,
    onItemClick: (itemVM: ItemVM) -> Unit
) {

    val navigator = rememberListDetailPaneScaffoldNavigator<ItemVM>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    val windowWidthSize =
        currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane(
                modifier = Modifier.preferredWidth(400.dp)
            ) {
                MultiModuleList(
                    content = content,
                    onItemClick = { itemVM ->
                        onItemClick(itemVM)
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                val backVisible = when (windowWidthSize) {
                    WindowWidthSizeClass.EXPANDED -> false
                    else -> true
                }
                selectedItem?.let {
                    ItemDetailsDetailsScreen(
                        itemVM = it,
                        showBackButton = backVisible,
                        onBackClick = {
                            navigator.navigateBack()
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun MultiModuleList(
    content: ContentVM,
    onItemClick: (itemVM: ItemVM) -> Unit
) {
    when (content.displayStyle) {
        HORIZONTAL_GRID -> MultiModuleHorizontalGrid(
            itemsList = content.items,
            gridState = content.gridState,
            onItemClick = onItemClick
        )

        VERTICAL_LIST -> MultiModuleVerticalList(
            mediaItems = content.items,
            listState = content.listState,
            onItemClick = onItemClick
        )
    }
}

@Preview
@Composable
fun ListDetailPaneScreenPreview() {

}