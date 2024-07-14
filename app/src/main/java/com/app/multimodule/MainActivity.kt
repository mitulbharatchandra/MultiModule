package com.app.multimodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.multimodule.feature.dashboard.ListDetailPaneScreen
import com.app.multimodule.ui.theme.MultiModuleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultiModuleTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                var currentDestination by rememberSaveable { mutableIntStateOf(0) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationSuiteScaffold(
                        modifier = Modifier.padding(innerPadding),
                        navigationSuiteItems = {
                            uiState.media?.items?.forEachIndexed { index, uiItem ->
                                item(
                                    selected = index == currentDestination,
                                    onClick = {
                                        currentDestination = index
                                    },
                                    icon = {
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(uiItem.thumbnail)
                                                .crossfade(false)
                                                .build(),
                                            contentDescription = uiItem.title,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clip(CircleShape)
                                        )
                                    },
                                    label = {
                                        Text(text = uiItem.title)
                                    }
                                )
                            }
                        },
                        layoutType = NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
                            currentWindowAdaptiveInfo()
                        )
                    ) {
                        uiState.media?.items?.get(currentDestination)?.content?.let {
                            ListDetailPaneScreen(
                                content = it,
                                selectedItem = uiState.selectedItem,
                                onItemClick = { itemVM ->
                                    viewModel.onEvent(MainUiEvent.OnItemClicked(itemVM))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiModuleTheme {
        Greeting("Android")
    }
}