package com.luna.core.extensions

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.luna.ui.theme.LunaTheme

fun ComponentActivity.setLunaContent(content: @Composable () -> Unit){
    setContent {
        LunaTheme {
            content()
        }
    }
}