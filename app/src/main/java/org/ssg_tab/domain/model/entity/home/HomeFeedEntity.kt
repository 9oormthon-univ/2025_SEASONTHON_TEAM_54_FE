package org.ssg_tab.domain.model.entity.home

import org.ssg_tab.data.dto.response.home.ContentsPages
import org.ssg_tab.data.dto.response.home.toNewsItem
import org.ssg_tab.presentation.ui.home.component.NewsItem

data class HomeFeedEntity(
    val contentsPage: ContentsPages,
)

fun HomeFeedEntity.toNewsItemList(): List<NewsItem> {
    return this.contentsPage.content.map { it.toNewsItem() }
}