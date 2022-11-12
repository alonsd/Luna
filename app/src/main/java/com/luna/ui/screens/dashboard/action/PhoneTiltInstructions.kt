package com.luna.ui.screens.dashboard.action

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luna.R
import com.luna.core.ui.dialog.LunaAlertDialog


@Composable
fun PhoneTiltInstructions(
    deviceAngle: String,
    submitButtonEnabled: Boolean,
    onConfirmClicked: () -> Unit
) {
    LunaAlertDialog(
        title = {
            Surface(
                color = Color.Blue,
                contentColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    text = stringResource(R.string.dashboard_phone_tilt_instructions_title),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                )
            }
        },
        text = {
            Column(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .fillMaxWidth(),
                    textStyle = TextStyle.Default.copy(fontSize = 12.sp),
                    value = stringResource(id = R.string.dashboard_phone_tilt_instructions, deviceAngle),
                    onValueChange = { }
                )
            }
        },
        onDismissRequest = { },
        confirmButton = {
            Button(onClick = { onConfirmClicked() }, enabled = submitButtonEnabled) {
                Text(text = stringResource(R.string.dashboard_phone_tilt_instructions_submit))
            }
        },
        dismissButton = {}
    )
}

@Preview
@Composable
fun PhoneTiltInstructionsPreview() {
    PhoneTiltInstructions("45", false) {}
}
