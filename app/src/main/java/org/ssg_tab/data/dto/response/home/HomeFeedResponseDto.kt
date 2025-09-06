package org.ssg_tab.data.dto.response.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.ssg_tab.domain.model.entity.home.HomeFeedEntity
import org.ssg_tab.presentation.ui.home.component.NewsItem

@Serializable
data class HomeFeedResponseDto(
    @SerialName("contentsPage")
    val contentsPage: ContentsPages,
)

@Serializable
data class ContentsPages(
    @SerialName("totalElements")
    val totalElements: Long,
    @SerialName("totalPages")
    val totalPages: Int,
    @SerialName("pageable")
    val pageable: Pageable,
    @SerialName("size")
    val size: Int,
    @SerialName("content")
    val content: List<ContentList>,
    @SerialName("number")
    val number: Int,
    @SerialName("sort")
    val sort: Sort,
    @SerialName("numberOfElements")
    val numberOfElements: Int,
    @SerialName("first")
    val first: Boolean,
    @SerialName("last")
    val last: Boolean,
    @SerialName("empty")
    val empty: Boolean,
)

@Serializable
data class Pageable(
    @SerialName("paged")
    val paged: Boolean,
    @SerialName("pageNumber")
    val pageNumber: Int,
    @SerialName("pageSize")
    val pageSize: Int,
    @SerialName("offset")
    val offset: Long,
    @SerialName("sort")
    val sort: Sort,
    @SerialName("unpaged")
    val unpaged: Boolean,
)

@Serializable
data class Sort(
    @SerialName("sorted")
    val sorted: Boolean,
    @SerialName("empty")
    val empty: Boolean,
    @SerialName("unsorted")
    val unsorted: Boolean,
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

@Serializable
data class CategoryList(
    @SerialName("categoryId")
    val categoryId: Long,
    @SerialName("name")
    val name: String,
)

fun HomeFeedResponseDto.toEntity(): HomeFeedEntity {
    return HomeFeedEntity(
        contentsPage = this.contentsPage,
    )
}

fun ContentList.toNewsItem(): NewsItem {
    return NewsItem(
        id = this.id,
        title = this.title,
        content = this.body,
        category = this.categories.firstOrNull()?.name ?: "기타",
        source = "SSG TAB",
        imageUrl = this.imageUrl ?: ""
    )
}