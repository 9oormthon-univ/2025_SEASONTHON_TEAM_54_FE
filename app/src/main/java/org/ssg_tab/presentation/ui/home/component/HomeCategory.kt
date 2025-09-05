package org.ssg_tab.presentation.ui.home.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabMainChip
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Preview(showBackground = true)
@Composable
private fun PreviewHomeCategory() {
    SsgTabTheme {
        HomeCategory(
            modifier = Modifier
        )
    }
}

@Composable
fun HomeCategory(
    modifier: Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_category_on),
            contentDescription = "category icon",
            tint = Color.Unspecified
        )
        LazyRow(
            modifier = Modifier
                .padding(start = 6.dp)
        ) {
            item {
                HomeCategoryChip(
                    title = "주식",
                    isEnabled = true,
                    onClick = { },
                    modifier = Modifier.padding(end = 6.dp)
                )
            }

            item {
                HomeCategoryChip(
                    title = "취업",
                    isEnabled = true,
                    onClick = { },
                    modifier = Modifier.padding(end = 6.dp)
                )
            }
        }


    }
}