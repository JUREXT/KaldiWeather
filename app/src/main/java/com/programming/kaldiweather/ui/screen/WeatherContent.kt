package com.programming.kaldiweather.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programming.kaldiweather.R
import com.programming.kaldiweather.ui.component.AutoCompleteTextField
import com.programming.kaldiweather.ui.component.ErrorView
import com.programming.kaldiweather.ui.component.LoadingView
import com.programming.kaldiweather.ui.component.SearchHistoryCard
import com.programming.kaldiweather.ui.theme.KaldiWeatherTheme

@Composable
fun WeatherContent(
    viewState: WeatherViewState,
    suggestionViewState: SuggestionViewState,
    searchHistoryViewState: SearchHistoryViewState,
    onSelectedSuggestion: (String) -> Unit,
    onExitClick: () -> Unit
) {

    val suggestions = listOf("Celje", "Laško", "ljubljana", "Koper")

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when (suggestionViewState) {
            SuggestionViewState.Idle -> {
                // Ignore
            }

            SuggestionViewState.Error -> {
                ErrorView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
            }

            SuggestionViewState.Loading -> {
                LoadingView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
            }

            is SuggestionViewState.Success -> {
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

        when (searchHistoryViewState) {
            SearchHistoryViewState.Idle -> {
                // Ignore
            }

            is SearchHistoryViewState.Data -> {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {
                    val list = searchHistoryViewState.history
                    items(list.count()) { index ->
                        SearchHistoryCard(
                            modifier = Modifier.padding(5.dp),
                            element = list[index]
                        )
                    }
                }
            }

            SearchHistoryViewState.NoData -> {
                // Ignore
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherContentWithSuggestionsPreview() {
    val suggestions = listOf("Celje", "Laško", "ljubljana", "Koper")

    KaldiWeatherTheme {
        WeatherContent(
            viewState = WeatherViewState.Idle,
            suggestionViewState = SuggestionViewState.Success(suggestions = suggestions),
            searchHistoryViewState = SearchHistoryViewState.NoData,
            onSelectedSuggestion = {},
            onExitClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherContentWithSuggestionErrorPreview() {
    KaldiWeatherTheme {
        WeatherContent(
            viewState = WeatherViewState.Idle,
            suggestionViewState = SuggestionViewState.Error,
            searchHistoryViewState = SearchHistoryViewState.NoData,
            onSelectedSuggestion = {},
            onExitClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherContentWithSuggestionLoadingPreview() {
    KaldiWeatherTheme {
        WeatherContent(
            viewState = WeatherViewState.Idle,
            suggestionViewState = SuggestionViewState.Loading,
            searchHistoryViewState = SearchHistoryViewState.NoData,
            onSelectedSuggestion = {},
            onExitClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherContentWithSuggestionLoadingAndSearchHistoryPreview() {
    val list = listOf("Celje", "Laško", "ljubljana", "Koper")

    KaldiWeatherTheme {
        WeatherContent(
            viewState = WeatherViewState.Idle,
            suggestionViewState = SuggestionViewState.Loading,
            searchHistoryViewState = SearchHistoryViewState.Data(history = list),
            onSelectedSuggestion = {},
            onExitClick = {}
        )
    }
}