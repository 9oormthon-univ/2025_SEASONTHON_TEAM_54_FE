package org.ssg_tab.data.dto.response.storage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.ssg_tab.data.dto.response.home.CategoryList

@Serializable
data class StorageResponseDto(
    @SerialName("contentsList")
    val contentsList: List<ContentList>,
)

@Serializable
data class ContentList(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("imageUrl")
    val imageUrl: String?,
    @SerialName("sourceUrl")
    val sourceUrl: String?,
    @SerialName("body")
    val body: String,
    @SerialName("categories")
    val categories: List<CategoryList>,
)

fun StorageResponseDto.toEntity(): StorageResponseDto = this