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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.ProgressContent
import com.example.androiddevchallenge.ui.StartButton
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlinx.coroutines.delay
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MyApp() {

    var startTime = 120.0
    var remainingTime: Double by rememberSaveable { mutableStateOf(startTime) }
    var isRunning: Boolean by rememberSaveable { mutableStateOf(false) }
    var countdownProgress: Float by rememberSaveable { mutableStateOf(0f) }

    LaunchedEffect(0) {
        while (true) {
            if (isRunning) {
                delay(100)
                remainingTime -= 0.1
                countdownProgress =
                    ((startTime - remainingTime) / startTime).toFloat().coerceIn(0.0f, 1.0f)
                if (remainingTime <= 0) {
                    isRunning = false
                    // countdownProgress = 0f
                    remainingTime = startTime
                } else if (!isRunning) {
                    countdownProgress = 0f
                }
            } else {
                delay(100)
            }
        }
    }

    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            ProgressContent(
                countdownProgress = countdownProgress,
                remainingTimeValue = ceil(remainingTime).toInt(),
                onValueChange = {
                    remainingTime = it.toDouble()
                },
                isRunning = isRunning
            )

            StartButton(
                isRunning = isRunning,
                setIsRunning = { newValue ->
                    if (!isRunning) {
                        startTime = remainingTime
                    } else {
                        countdownProgress = 0f
                        remainingTime = startTime
                    }
                    isRunning = newValue
                }
            )
        }
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
