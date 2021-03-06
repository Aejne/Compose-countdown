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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

@ExperimentalAnimationApi
@Composable
fun EditTimeContent(
    modifier: Modifier = Modifier,
    remainingTimeValue: Int,
    updateTime: (Int) -> Unit,
    isRunning: Boolean
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        EditTimeItem(
            modifier = Modifier.width(40.dp),
            remainingTimeValue = remainingTimeValue / 60,
            updateValue = { increase ->
                if (increase) {
                    updateTime(remainingTimeValue + 60)
                } else {
                    updateTime((remainingTimeValue - 60).coerceAtLeast(0))
                }
            },
            isRunning = isRunning
        )
        Text(
            text = ":",
            modifier = Modifier.padding(horizontal = 4.dp),
            fontSize = 26.sp,
            color = MaterialTheme.colors.onSurface
        )
        EditTimeItem(
            modifier = Modifier.width(40.dp),
            remainingTimeValue = remainingTimeValue % 60,
            updateValue = { increase ->
                if (increase) {
                    updateTime(remainingTimeValue + 1)
                } else {
                    updateTime((remainingTimeValue - 1).coerceAtLeast(0))
                }
            },
            isRunning = isRunning
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun EditTimeItem(
    modifier: Modifier = Modifier,
    remainingTimeValue: Int,
    updateValue: (Boolean) -> Unit,
    isRunning: Boolean
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AnimatedVisibility(
            visible = !isRunning,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropUp, contentDescription = "Increase",
                modifier = modifier
                    .clickable { updateValue(true) }
                    .padding(8.dp),
                tint = MaterialTheme.colors.onSurface
            )
        }

        Text(
            text = remainingTimeValue.toString(),
            modifier = Modifier
                .padding(vertical = 2.dp),
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            color = MaterialTheme.colors.onSurface
        )

        AnimatedVisibility(
            visible = !isRunning,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Decrease",
                modifier = modifier
                    .clickable {
                        updateValue(false)
                    }
                    .padding(8.dp),
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

@ExperimentalAnimationApi
@Preview("Light theme item")
@Composable
fun EditTimeItemLight() {
    MyTheme {
        EditTimeItem(
            modifier = Modifier.width(32.dp),
            remainingTimeValue = 1,
            updateValue = { /*TODO*/ },
            isRunning = false
        )
    }
}

@ExperimentalAnimationApi
@Preview("Dark theme item")
@Composable
fun EditTimeItemDark() {
    MyTheme(darkTheme = true) {
        EditTimeItem(
            modifier = Modifier.width(32.dp),
            remainingTimeValue = 10,
            updateValue = { /*TODO*/ },
            isRunning = false
        )
    }
}
