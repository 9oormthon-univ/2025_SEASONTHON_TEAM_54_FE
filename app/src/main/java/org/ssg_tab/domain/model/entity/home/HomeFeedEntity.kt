package org.ssg_tab.domain.model.entity.home

import org.ssg_tab.data.dto.response.home.ContentList
import org.ssg_tab.data.dto.response.home.ContentsPages
import org.ssg_tab.data.dto.response.home.Parentsorts
import org.ssg_tab.data.dto.response.home.toNewsItem
import org.ssg_tab.presentation.ui.home.component.NewsItem

data class HomeFeedEntity(
    val contentsPage: ContentsPages,
    val size: Int,
    val content: List<ContentList>,
    val number: Int,
    val sort: Parentsorts,
    val numberOfElements: Int,
    val first: Boolean,
    val last: Boolean,
    val empty: Boolean,
)

fun HomeFeedEntity.toNewsItemList(): List<NewsItem> {
    return this.content.map { it.toNewsItem() }
}