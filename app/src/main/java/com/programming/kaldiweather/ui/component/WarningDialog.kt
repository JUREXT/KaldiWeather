package com.programming.kaldiweather.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.programming.kaldiweather.ui.theme.KaldiWeatherTheme

@Composable
fun WarningDialog(
    title: String? = null,
    message: String? = null,
    positiveButton: String? = null,
    negativeButton: String? = null,
    onPositiveClick: (() -> Unit)? = null,
    onNegativeClick: (() -> Unit)? = null,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = true
        )
    ) {
        Column(
            modifier = Modifier
                .widthIn(min = 280.dp, max = 560.dp)
                .padding(horizontal = 24.dp)
                .shadow(elevation = 8.dp)
                .background(Color.White)
        ) {
            if (title != null) {
                Text(
                    text = title,
                    style = TextStyle.Default,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            top = 24.dp,
                            end = 24.dp
                        )
                )
            }
            if (message != null) {
                Text(
                    text = message,
                    style = TextStyle.Default,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            top = if (title != null) 16.dp else 0.dp,
                            end = 24.dp
                        )
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                if (negativeButton != null) {
                    Text(
                        text = negativeButton.uppercase(),
                        style = TextStyle.Default,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable(
                            role = Role.Button,
                            onClick = onNegativeClick ?: {}
                        )
                    )
                }

                if (positiveButton != null) {
                    Text(
                        text = positiveButton.uppercase(),
                        style = TextStyle.Default,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable(
                            role = Role.Button,
                            onClick = onPositiveClick ?: {}
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WarningDialogPreview() {
    KaldiWeatherTheme {
        WarningDialog(
            title = "Title",
            message = "This is some longer message for you. We need to test how this longer text looks.",
            positiveButton = "Positive",
            negativeButton = "Negative",
            onDismissRequest = {}
        )
    }
}