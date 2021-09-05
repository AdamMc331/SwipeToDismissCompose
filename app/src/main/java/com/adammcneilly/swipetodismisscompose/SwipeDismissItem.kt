package com.adammcneilly.swipetodismisscompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun SwipeDismissItem(
    content: @Composable () -> Unit,
    onDismissed: (isDismissed: Boolean) -> Unit,
    visible: Boolean,
) {

    // Tips for understanding differences: https://dev.to/zachklipp/remember-mutablestateof-a-cheat-sheet-10ma
    val hasTriedToDismiss = remember { mutableStateOf(false) }
    var hasConfirmedDismissal: Boolean by remember { mutableStateOf(false) }

    val dismissState = rememberDismissState()
    val dismissedToEnd = dismissState.isDismissed(DismissDirection.StartToEnd)
    val dismissedToStart = dismissState.isDismissed(DismissDirection.EndToStart)
    val isDismissed = (dismissedToEnd || dismissedToStart)

    onDismissed.invoke(isDismissed)

    val contentOffset = if (hasTriedToDismiss.value) {
        48.dp
    } else {
        0.dp
    }

    val coroutineScope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = visible,
    ) {
        SwipeToDismiss(
            state = dismissState,
            directions = setOf(DismissDirection.StartToEnd),
            background = {
                SwipeBackground(
                    onDeleteClicked = {
                        hasConfirmedDismissal = true

                        coroutineScope.launch {
                            dismissState.dismiss(DismissDirection.StartToEnd)
                        }
                    }
                )
            },
        ) {
            Box(
                modifier = Modifier.offset(x = contentOffset),
            ) {
                content()
            }
        }
    }
}
