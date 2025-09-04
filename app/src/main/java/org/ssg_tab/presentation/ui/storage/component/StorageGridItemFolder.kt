package org.ssg_tab.presentation.ui.storage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Preview(showBackground = true)
@Composable
private fun PreviewStorageGridItemFolder() {
    SsgTabTheme {
        StorageGridItemFolder(
            folderName = "폴더 이름"
        )
    }
}

@Composable
fun StorageGridItemFolder(
    folderName: String,
    modifier: Modifier = Modifier,

    ) {
    Box(
        modifier = modifier
            .background(
                color = Color.Transparent
            ),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_storage_fold),
            contentDescription = "폴더 아이콘",
            tint = Color.Unspecified
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 12.dp, end = 16.dp),
            text = folderName,
            style = SsgTabTheme.typography.Small_Sb,
            color = SsgTabTheme.colors.White
        )
    }
}