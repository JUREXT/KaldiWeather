package com.programming.kaldiweather.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programming.kaldiweather.R
import com.programming.kaldiweather.ui.component.AutoCompleteTextField
import com.programming.kaldiweather.ui.component.GreetingText
import com.programming.kaldiweather.ui.theme.KaldiWeatherTheme

@Composable
fun WeatherContent(
    viewState: WeatherViewState,
    suggestionViewState: SuggestionViewState,
    onSelectedSuggestion: (String) -> Unit,
    onExitClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GreetingText(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    role = Role.Button,
                    onClick = onExitClick
                )
                .padding(10.dp),
            name = "Android"
        )
        GreetingText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            name = viewState.toString()
        )

        if (suggestionViewState is SuggestionViewState.Loading) {
            // Ignore
        } else if (suggestionViewState is SuggestionViewState.Success) {
            AutoCompleteTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                suggestions = suggestionViewState.suggestions,
                enterSuggestionRes = R.string.enter_suggestion_city_text,
                nothingEnteredRes = R.string.suggestion_not_present,
                onSelectedSuggestion = onSelectedSuggestion
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherContentPreview() {
    val suggestions = listOf("Celje", "Laško", "ljubljana", "Koper")

    KaldiWeatherTheme {
        WeatherContent(
            viewState = WeatherViewState.Idle,
            suggestionViewState = SuggestionViewState.Success(suggestions = suggestions),
            onSelectedSuggestion = {},
            onExitClick = {}
        )
    }
}