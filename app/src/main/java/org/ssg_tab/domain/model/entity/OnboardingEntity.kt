package org.ssg_tab.domain.model.entity

data class OnboardingEntity (
    val ageBand: String,
    val region: String,
    val job: String,
    val categoryIds: List<Long>
)