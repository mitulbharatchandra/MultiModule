package com.app.multimodule.core.resource_assets.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val displayStyle: DisplayStyle,
    val items: List<Item>
)

@Serializable
data class Content(
    val displayStyle: DisplayStyle,
    val items: List<Item>
)

@Serializable
data class Item(
    val content: Content? = null,
    val id: String,
    val subtitle: String? = null,
    val thumbnail: String? = null,
    val title: String
)

@Serializable
enum class DisplayStyle(val displayStyle: String) {
    @SerialName("grid")
    GRID("grid"),
    @SerialName("list")
    LIST("list")
}