package com.luna.ui.screens.dashboard.state.initial

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalComposeUiApi
@Composable
fun DashboardInitialState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = com.luna.R.drawable.luna),
            contentDescription = "",
            Modifier.size(200.dp, 200.dp)
        )
        Button(onClick = { }, Modifier.padding(top = 16.dp)) {
            Text(text = "Start")
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
fun DashboardInitialStatePreview() {
    DashboardInitialState()
}