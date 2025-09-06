package org.ssg_tab.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Preview(showBackground = true)
@Composable
private fun PreviewHomeMockComponent(){

    SsgTabTheme {
        HomeMockComponent()
    }
}

@Composable
fun HomeMockComponent (
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .height(36.dp)
            .fillMaxWidth()
            .background(
                color = SsgTabTheme.colors.White,
                shape = RoundedCornerShape(
                    bottomStart = 36.dp, bottomEnd = 36.dp)
            )
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(
                    bottomStart = 36.dp, bottomEnd = 36.dp),
                spotColor = SsgTabTheme.colors.MidGray,
                ambientColor = SsgTabTheme.colors.MidGray,
            )
            .padding(horizontal = 20.dp)
    ) {

    }
}