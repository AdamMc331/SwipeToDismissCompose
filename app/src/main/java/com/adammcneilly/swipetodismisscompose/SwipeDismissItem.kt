package com.adammcneilly.swipetodismisscompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun SwipeDismissItem(
    background: @Composable RowScope.() -> Unit,
    content: @Composable RowScope.() -> Unit,
    onDismissed: (isDismissed: Boolean) -> Unit,
) {
    val dismissState = rememberDismissState()
    val dismissedToEnd = dismissState.isDismissed(DismissDirection.StartToEnd)
    val dismissedToStart = dismissState.isDismissed(DismissDirection.EndToStart)
    val isDismissed = (dismissedToEnd || dismissedToStart)

    onDismissed.invoke(isDismissed)

    AnimatedVisibility(visible = !isDismissed) {
        SwipeToDismiss(
            state = dismissState,
            background = background,
            dismissContent = content,
        )
    }
}
