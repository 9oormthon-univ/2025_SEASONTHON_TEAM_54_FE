package org.ssg_tab.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Composable
fun JobPostingCard(
    job: JobPosting,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var isBookmarked by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = job.title,
                style = SsgTabTheme.typography.Regular_Sb.copy(
                    fontSize = 22.sp,
                    color = SsgTabTheme.colors.Black,
                    lineHeight = 1.5.em
                ),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))

        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = job.company,
            style = SsgTabTheme.typography.Regular_Sb.copy(
                fontSize = 15.sp,
                color = SsgTabTheme.colors.Black,
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = SsgTabTheme.colors.White,
                        RoundedCornerShape(20.dp)
                    )
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(20.dp),
                        color = SsgTabTheme.colors.MainBlue,
                    )
                    .padding(horizontal = 15.dp, vertical = 8.dp)
            ) {
                Text(
                    text = job.category,
                    style = SsgTabTheme.typography.Regular_Sb.copy(
                        fontSize = 13.sp,
                        color = SsgTabTheme.colors.MainBlue,
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            JobInfoChip(text = job.location)
            JobInfoChip(text = job.contractType)
            JobInfoChip(text = job.workHours)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "월급",
                style = SsgTabTheme.typography.Regular_Sb.copy(
                    fontSize = 16.sp,
                    color = SsgTabTheme.colors.MainBlue,
                )
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = job.salary,
                style = SsgTabTheme.typography.Regular_Sb.copy(
                    fontSize = 18.sp,
                    color = SsgTabTheme.colors.LightGray,
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "모집분야 및 지원자격",
                style = SsgTabTheme.typography.Regular_Sb.copy(
                    fontSize = 16.sp,
                    color = SsgTabTheme.colors.MainBlue,
                )
            )

            JobDetailItem(label = "모집직무", value = job.jobType)
            JobDetailItem(label = "담당업무", value = job.duties)
            JobDetailItem(label = "채용인원", value = job.recruitCount)
            JobDetailItem(label = "경력사항", value = job.experience)
            JobDetailItem(label = "학력사항", value = job.education)
            JobDetailItem(label = "장애유형", value = job.disabilityType)
        }

        Spacer(modifier = Modifier.height(29.dp))

        Button(
            onClick = { navController.navigate("jobpost_detail/${job.id}") },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SsgTabTheme.colors.MainBlue,
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "공고 보러가기",
                style = SsgTabTheme.typography.Regular_Sb.copy(
                    fontSize = 15.sp,
                    color = SsgTabTheme.colors.White,
                ),
            )
        }
    }
}

@Composable
private fun JobInfoChip(
    text: String,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .background(
                color = SsgTabTheme.colors.LightGray,
                RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            style = SsgTabTheme.typography.Regular_R.copy(
                fontSize = 13.sp,
                color = SsgTabTheme.colors.LightGray
            )
        )
    }
}

@Composable
private fun JobDetailItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            style = SsgTabTheme.typography.Regular_R.copy(
                fontSize = 15.sp,
                color = SsgTabTheme.colors.LightGray
            ),
            modifier = Modifier.width(80.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = value,
            style = SsgTabTheme.typography.Regular_R.copy(
                fontSize = 15.sp,
                color = SsgTabTheme.colors.LightGray
            ),
            modifier = Modifier.weight(1f),
            overflow = TextOverflow.Ellipsis
        )
    }
}