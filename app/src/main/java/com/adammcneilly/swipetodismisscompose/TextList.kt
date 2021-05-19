package com.adammcneilly.swipetodismisscompose

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextList(texts: List<String>) {
    LazyColumn {
        items(texts) { text ->
            TextListItem(text = text)

            Divider()
        }
    }
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
private fun TextListPreview() {
    val texts = (1..10).map { index ->
        "List Item $index"
    }

    TextList(texts)
}
