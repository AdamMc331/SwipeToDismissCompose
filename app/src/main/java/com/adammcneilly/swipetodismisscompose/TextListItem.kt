package com.adammcneilly.swipetodismisscompose

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.swipetodismisscompose.ui.theme.SwipeToDismissComposeTheme

@Composable
fun TextListItem(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(12.dp),
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun TextListItemPreview() {
    SwipeToDismissComposeTheme {
        Surface {
            TextListItem("Testy McTestFace")
        }
    }
}
