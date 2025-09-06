package org.ssg_tab.presentation.ui.userinformation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabButton
import org.ssg_tab.core.designsystem.component.SsgTabTopBar
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.presentation.ui.userinformation.model.OnboardingUiState
import org.ssg_tab.presentation.ui.userinformation.model.OnboardingViewModel

@Composable
fun SelectJobScreen(
    viewModel: OnboardingViewModel,
    onNavigateBack: () -> Unit,
    onOnboardingComplete: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.onboardingComplete) {
        if (uiState.onboardingComplete) {
            onOnboardingComplete()
        }
    }

    SelectJobScreenContent(
        uiState = uiState,
        onJobSelected = viewModel::selectJob,
        onNavigateBack = onNavigateBack,
        onNextClick = viewModel::submitOnboardingData
    )
}

@Composable
fun SelectJobScreenContent(
    uiState: OnboardingUiState,
    onJobSelected: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNextClick: () -> Unit
) {
    val jobs = listOf("직장인", "취준생", "학생", "프리랜서", "사업자", "공무원", "기타")

    val jobImages = listOf(
        R.drawable.ic_job_1,
        R.drawable.ic_job_2,
        R.drawable.ic_job_3,
        R.drawable.ic_job_4,
        R.drawable.ic_job_5,
        R.drawable.ic_job_6,
        R.drawable.ic_job_7
    )

    val selectedIndex = jobs.indexOf(uiState.selectedJob)
    val isButtonEnabled = uiState.selectedJob != null

    Scaffold(
        containerColor = SsgTabTheme.colors.White,
        topBar = {
            SsgTabTopBar(
                leftIcon = R.drawable.ic_core_back,
                onLeftIconClick = onNavigateBack,
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
                selectedItem = uiState.selectedJob,
                onItemSelected = onJobSelected
            )

            Spacer(modifier = Modifier.height(60.dp))

            AnimatedVisibility(
                visible = selectedIndex != -1,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.fillMaxWidth()
            ) {
                if (selectedIndex != -1) {
                    Image(
                        painter = painterResource(id = jobImages[selectedIndex-1]),
                        contentDescription = "직업 이미지",
                        modifier = Modifier.size(250.dp)
                    )

                }
            }

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

@Preview(showBackground = true, name = "Job Selected State")
@Composable
private fun SelectJobScreen_Selected_Preview() {
    SsgTabTheme {
        SelectJobScreenContent(
            uiState = OnboardingUiState(selectedJob = "프리랜서"),
            onJobSelected = {},
            onNavigateBack = {},
            onNextClick = {}
        )
    }
}