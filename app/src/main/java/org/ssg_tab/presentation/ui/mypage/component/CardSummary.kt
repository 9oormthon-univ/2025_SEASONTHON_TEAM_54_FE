package org.ssg_tab.presentation.ui.mypage.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme


@Composable
fun CardSummary(readCount: Int, favoriteCount: Int) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SummaryItem(modifier = Modifier.weight(1f), count = readCount, label = "읽은 카드")
            VerticalDivider(modifier = Modifier.height(40.dp), color = SsgTabTheme.colors.SoftGray)
            SummaryItem(modifier = Modifier.weight(1f), count = favoriteCount, label = "즐겨찾는 카드")
        }
    }
}

@Composable
fun SummaryItem(modifier: Modifier = Modifier, count: Int, label: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(text = count.toString(), style = SsgTabTheme.typography.header)
        Text(text = label, style = SsgTabTheme.typography.Small_R, color = SsgTabTheme.colors.TextBlue)
    }
}

