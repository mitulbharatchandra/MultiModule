package com.app.multimodule.core.common.model

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
    /*@SerialName("vertical_grid")
    VERTICAL_GRID("vertical_grid"),*/

    @SerialName("horizontal_grid")
    HORIZONTAL_GRID("horizontal_grid"),

    @SerialName("vertical_list")
    VERTICAL_LIST("vertical_list"),

    /*@SerialName("horizontal_list")
    HORIZONTAL_LIST("horizontal_list")*/
}