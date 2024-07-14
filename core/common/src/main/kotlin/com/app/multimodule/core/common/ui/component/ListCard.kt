package com.app.multimodule.core.common.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.multimodule.core.common.model.ItemVM

@Composable
fun ListCard(
    itemContent: ItemVM,
    onItemClick: (itemVM: ItemVM) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick = { onItemClick(itemContent) },
        modifier = modifier
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(itemContent.thumbnail)
                .crossfade(true)
                .build(),
            contentDescription = itemContent.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

    }
}