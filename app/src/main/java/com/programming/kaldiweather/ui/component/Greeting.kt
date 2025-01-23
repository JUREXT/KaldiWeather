package com.programming.kaldiweather.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.programming.kaldiweather.ui.theme.KaldiWeatherTheme

@Composable
fun GreetingText(
    modifier: Modifier = Modifier,
    name: String
) {
    Text(
        modifier = modifier,
        text = "Hello $name!"
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingTextPreview() {
    KaldiWeatherTheme {
        GreetingText(name = "Android")
    }
}