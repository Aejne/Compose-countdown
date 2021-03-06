/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun ProgressContent(
    countdownProgress: Float,
    remainingTimeValue: Int,
    onValueChange: (Int) -> Unit,
    isRunning: Boolean
) {

    val animatedProgress by animateFloatAsState(
        targetValue = countdownProgress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    val arcColor = animateColorAsState(
        targetValue = if (countdownProgress < 0.8f) {
            Color.Green
        } else {
            Color.Red
        }
    )

    Box(
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            progress = 1.0f,
            modifier = Modifier.requiredSize(240.dp),
            color = Color.Gray,
            strokeWidth = 4.dp
        )

        CircularProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier.requiredSize(240.dp),
            color = arcColor.value,
            strokeWidth = 4.dp
        )

        EditTimeContent(
            remainingTimeValue = remainingTimeValue,
            updateTime = { newTime ->
                onValueChange(newTime)
            },
            isRunning = isRunning
        )
    }
}
