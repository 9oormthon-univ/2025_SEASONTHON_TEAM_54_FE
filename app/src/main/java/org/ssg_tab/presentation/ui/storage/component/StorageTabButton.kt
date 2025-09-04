package org.ssg_tab.presentation.ui.storage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Preview(showBackground = true)
@Composable
private fun PreviewToggleButton() {
    SsgTabTheme {
        Column {
            var selected1 by remember { mutableStateOf("최신순") }
            StorageToggleSection(
                selectedSortType = selected1,
                onSortTypeChanged = { selected1 = it }
            )
        }
    }
}

@Composable
fun StorageTabButton(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .width(190.dp)
            .background(
                color = SsgTabTheme.colors.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(3.dp),
    ) {
        options.forEach { option ->
            ToggleButton(
                text = option,
                isSelected = selectedOption == option,
                onClick = { onOptionSelected(option) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ToggleButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = if (isSelected) SsgTabTheme.colors.White else Color.Transparent,
                shape = RoundedCornerShape(6.dp)
            )
            .clip(RoundedCornerShape(6.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) SsgTabTheme.colors.Black else SsgTabTheme.colors.SoftGray
        )
    }
}

@Composable
fun StorageToggleSection(
    selectedSortType: String,
    onSortTypeChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    StorageTabButton(
        options = listOf("최신순", "답기별"),
        selectedOption = selectedSortType,
        onOptionSelected = onSortTypeChanged,
        modifier = modifier.width(120.dp)
    )
}

