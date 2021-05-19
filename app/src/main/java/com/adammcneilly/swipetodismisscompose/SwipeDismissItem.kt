package com.adammcneilly.swipetodismisscompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SwipeDismissItem(
    content: @Composable () -> Unit,
) {
    val dismissState = rememberDismissState()

    val isDismissed = dismissState.isDismissed(DismissDirection.StartToEnd)

    AnimatedVisibility(visible = !isDismissed) {
        SwipeToDismiss(
            state = dismissState,
            background = {
                Surface(
                    color = Color.Red,
                    modifier = Modifier.fillMaxSize()
                ) {

                }
            },
            dismissContent = {
                content()
            }
        )
    }
}