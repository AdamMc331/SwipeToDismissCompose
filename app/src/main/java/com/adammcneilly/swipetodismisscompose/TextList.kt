package com.adammcneilly.swipetodismisscompose

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TextList(texts: List<String>) {
    val itemListState = remember {
        mutableStateOf(texts)
    }

    LazyColumn {
        items(
            items = itemListState.value,
            key = { item ->
                item
            },
        ) { text ->
            SwipeDismissItem(
                background = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Red)
                    )
                },
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextListItem(text = text)

                        Divider(
                            color = Color.DarkGray,
                        )
                    }
                },
                onDismissed = { isDismissed ->
                    Log.d("TextList", "IsDismissed: $isDismissed; Text: $text")

                    if (isDismissed) {
                        itemListState.value = itemListState.value.filter { it != text }
                    }
                }
            )
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
