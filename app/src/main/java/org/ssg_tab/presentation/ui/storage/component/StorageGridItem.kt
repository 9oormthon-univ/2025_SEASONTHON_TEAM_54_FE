package org.ssg_tab.presentation.ui.storage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Preview(showBackground = true)
@Composable
private fun PreviewStorageGridItem() {
    SsgTabTheme {
        StorageGridItem(
            title = "상품명",
            imageUrl = "https://thumbnews.nateimg.co.kr/view610///news.nateimg.co.kr/orgImg/mt/2021/01/21/2021012110232011092_1.jpg",
            isLiked = false,
        )
    }
}

@Composable
fun StorageGridItem(
    title: String,
    imageUrl: String?,
    isLiked: Boolean,
    modifier: Modifier = Modifier,
    defaultBackgroundColor: Color = SsgTabTheme.colors.LightBlue,
) {
    Box(
        modifier = modifier
            .background(
                color = defaultBackgroundColor,
                shape = RoundedCornerShape(20.dp),
            )
            .size(120.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        if (!imageUrl.isNullOrBlank()) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 13.dp),
            text = title,
            color = SsgTabTheme.colors.White,
            style = SsgTabTheme.typography.Small_Sb.copy(
                fontSize = 8.sp
            )
        )
        Icon(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_storage_like),
            contentDescription = "좋아요",
            tint = if (isLiked) Color.Unspecified.copy(alpha = 0F) else Color.Unspecified
        )
    }
}