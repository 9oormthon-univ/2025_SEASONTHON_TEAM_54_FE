package org.ssg_tab.presentation.ui.storage.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

enum class TimeTabType(val displayText: String) {
    WEEK("주간"),
    MONTH("월간"),
    YEAR("연간")
}

@Preview(showBackground = true)
@Composable
private fun PreviewStorageTimeTabButton() {
    SsgTabTheme {
        var selectedTab by remember { mutableStateOf(TimeTabType.WEEK) }

        Column {
            StorageTimeTabButton(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "선택된 탭: ${selectedTab.displayText}")
        }
    }
}

@Composable
fun StorageTimeTabButton(
    selectedTab: TimeTabType,
    onTabSelected: (TimeTabType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TimeTabType.values().forEach { tab ->
                val isSelected = selectedTab == tab

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { onTabSelected(tab) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = tab.displayText,
                        style = SsgTabTheme.typography.Regular_Sb,
                        color = if (isSelected) {
                            SsgTabTheme.colors.Black
                        } else {
                            SsgTabTheme.colors.SoftGray
                        },
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = SsgTabTheme.colors.WhiteGray
        )
    }
}