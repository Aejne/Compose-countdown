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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StartButton(isRunning: Boolean, setIsRunning: (Boolean) -> Unit) {
    TextButton(
        onClick = { setIsRunning(!isRunning) },
        modifier = Modifier.padding(top = 24.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colors.onBackground
        ),
        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground)
    ) {
        Text(text = if (isRunning) "Stop" else "Start")
    }
}
