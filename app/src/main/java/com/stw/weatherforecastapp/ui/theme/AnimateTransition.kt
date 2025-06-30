package com.stw.weatherforecastapp.ui.theme

import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

const val EXPANSTION_TRANSITION_DURATION = 450

val ENTER_TRANSITION = expandVertically(
    expandFrom = Alignment.Top,
    animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
) + fadeIn(
    initialAlpha = 0.3f,
    animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
)
val EXIT_TRANSITION = shrinkVertically(
    shrinkTowards = Alignment.Top,
    animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
) + fadeOut(
    animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
)