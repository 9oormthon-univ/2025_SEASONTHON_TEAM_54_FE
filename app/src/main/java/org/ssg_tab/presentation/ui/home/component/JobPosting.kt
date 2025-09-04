package org.ssg_tab.presentation.ui.home.component

data class JobPosting(
    val id: Int,
    val title: String,
    val company: String,
    val category: String,
    val location: String,
    val contractType: String,
    val workHours: String,
    val salary: String,
    val jobType: String,
    val duties: String,
    val recruitCount: String,
    val experience: String,
    val education: String,
    val disabilityType: String
)