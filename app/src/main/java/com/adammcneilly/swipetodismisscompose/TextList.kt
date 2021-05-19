package com.adammcneilly.swipetodismisscompose

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun TextList(texts: List<String>) {
    LazyColumn {
        items(texts) { text ->
            SwipeDismissItem {
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextListItem(text = text)

                    Divider()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
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
