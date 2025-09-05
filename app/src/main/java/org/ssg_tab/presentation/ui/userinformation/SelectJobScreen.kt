package org.ssg_tab.presentation.ui.userinformation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabButton
import org.ssg_tab.core.designsystem.component.SsgTabTopBar
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Composable
fun SelectJobScreen() {
    val jobs = listOf("직장인", "취준생", "학생", "프리랜서", "사업자", "공무원", "기타")

    var selectedJob by remember { mutableStateOf<String?>(null) }

    val isButtonEnabled = selectedJob != null

    SelectJobScreenContent(
        jobs = jobs,
        selectedJob = selectedJob,
        isButtonEnabled = isButtonEnabled,
        onJobSelected = { job ->
            selectedJob = if (selectedJob == job) null else job
        },
        onNextClick = {
            // TODO: '다음' 버튼 클릭 로직
        }
    )
}

@Composable
fun SelectJobScreenContent(
    jobs: List<String>,
    selectedJob: String?,
    isButtonEnabled: Boolean,
    onJobSelected: (String) -> Unit,
    onNextClick: () -> Unit
) {
    Scaffold(
        containerColor = SsgTabTheme.colors.White,
        topBar = {
            SsgTabTopBar(
                leftIcon = R.drawable.ic_core_back,
                middleText = "",
                rightIcon = null,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_progress_bar_3),
                contentDescription = "progress_bar_step3",
                tint = Color.Unspecified,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 메인 타이틀
            Text(text = "지금 어떤 일을 하고 계신가요?", style = SsgTabTheme.typography.header)

            Spacer(modifier = Modifier.height(40.dp))

            SelectableChipGrid(
                items = jobs,
                selectedItem = selectedJob,
                onItemSelected = onJobSelected
            )

            Spacer(modifier = Modifier.weight(1f))

            // 다음 버튼
            SsgTabButton(
                name = "다음",
                isEnabled = isButtonEnabled,
                onClick = onNextClick
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


@Preview(showBackground = true, name = "Initial State")
@Composable
private fun SelectJobScreen_Initial_Preview() {
    SsgTabTheme {
        SelectJobScreen()
    }
}

@Preview(showBackground = true, name = "Job Selected State")
@Composable
private fun SelectJobScreen_Selected_Preview() {
    SsgTabTheme {
        SelectJobScreenContent(
            jobs = listOf("직장인", "취준생", "학생", "프리랜서", "사업자", "공무원", "기타"),
            selectedJob = "취준생",
            isButtonEnabled = true,
            onJobSelected = {},
            onNextClick = {}
        )
    }
}