package org.ssg_tab.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.presentation.ui.home.component.HomeActionButton
import org.ssg_tab.presentation.ui.home.component.HomeCategory
import org.ssg_tab.presentation.ui.home.component.HomeMainForm
import org.ssg_tab.presentation.ui.home.component.HomeTopBar
import org.ssg_tab.presentation.ui.home.component.JobPosting
import org.ssg_tab.presentation.ui.home.model.HomeViewModel

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SsgTabTheme{
        HomeScreen(
            paddingValues = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    modifier : Modifier = Modifier,
) {
    var searchText by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return Offset.Zero
            }
        }
    }

    Column (
        modifier = modifier
            .padding(paddingValues)
            .nestedScroll(nestedScrollConnection)
            .background(
                color = SsgTabTheme.colors.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        HomeTopBar(onClick = {})
        HomeCategory(modifier = Modifier)
        HomeMainForm(
            jobPostings = List(5) {
                JobPosting(
                    id = it,
                    title = "Sample Job Title $it",
                    company = "Sample Company $it",
                    category = "Sample Category $it",
                    location = "Sample Location $it",
                    contractType = "Sample Contract Type $it",
                    workHours = "Sample Work Hours $it",
                    salary = "Sample Salary $it",
                    jobType = "Sample Job Type $it",
                    duties = "Sample Duties $it",
                    recruitCount = "Sample Recruit Count $it",
                    experience = "Sample Experience $it",
                    education = "Sample Education $it",
                    disabilityType = "Sample Disability Type $it",
                )
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f),
            navController = navController
        )
        HomeActionButton(onClick = {})

        Spacer(modifier = Modifier.padding(14.dp))
    }

}

