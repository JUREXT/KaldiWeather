package com.programming.kaldiweather.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programming.kaldiweather.R
import com.programming.kaldiweather.connection.ConnectivityObserver
import com.programming.kaldiweather.ui.component.AutoCompleteTextField
import com.programming.kaldiweather.ui.component.LoadingView
import com.programming.kaldiweather.ui.component.SearchHistoryCard
import com.programming.kaldiweather.ui.component.StatusView
import com.programming.kaldiweather.ui.component.WeatherDetails
import com.programming.kaldiweather.ui.domain.model.Forecast
import com.programming.kaldiweather.ui.theme.KaldiWeatherTheme

@Composable
fun WeatherContent(
    viewState: WeatherViewState,
    suggestionViewState: SuggestionViewState,
    searchHistoryViewState: SearchHistoryViewState,
    connectivityStatus: ConnectivityObserver.ConnectivityStatus,
    onSelectedSuggestion: (String) -> Unit,
    onSelectedHistory: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (connectivityStatus == ConnectivityObserver.ConnectivityStatus.Lost) {
            StatusView(
                modifier = Modifier.fillMaxWidth(),
                errorMessageRes = connectivityStatus.messageRes,
                color = Color.Red
            )
        }

        when (suggestionViewState) {
            is SuggestionViewState.Suggestions -> {
                AutoCompleteTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    suggestions = suggestionViewState.suggestions,
                    enterSuggestionRes = R.string.enter_suggestion_city_text,
                    nothingEnteredRes = R.string.suggestion_not_present,
                    onSelectedSuggestion = onSelectedSuggestion
                )
            }
        }

        when (searchHistoryViewState) {
            is SearchHistoryViewState.History -> {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                ) {
                    val list = searchHistoryViewState.history
                    items(list.count()) { index ->
                        SearchHistoryCard(
                            modifier = Modifier.padding(5.dp),
                            element = list[index],
                            onSelected = onSelectedHistory
                        )
                    }
                }
            }
        }

        when (viewState) {
            WeatherViewState.Error -> {
                // Ignore
            }

            WeatherViewState.Exit -> {
                // Ignore
            }

            WeatherViewState.Idle -> {
                // Ignore
            }

            WeatherViewState.Loading -> LoadingView(
                modifier = Modifier.fillMaxWidth()
            )

            is WeatherViewState.Success -> {
                WeatherDetails(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    forecast = viewState.forecast
                )
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
            suggestionViewState = SuggestionViewState.Suggestions(suggestions = suggestions),
            searchHistoryViewState = SearchHistoryViewState.History(history = suggestions),
            connectivityStatus = ConnectivityObserver.ConnectivityStatus.Lost,
            onSelectedSuggestion = {},
            onSelectedHistory = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherContentWithSuggestionErrorPreview() {
    val suggestions = listOf("Celje", "Laško", "ljubljana", "Koper")

    KaldiWeatherTheme {
        WeatherContent(
            viewState = WeatherViewState.Idle,
            suggestionViewState = SuggestionViewState.Suggestions(suggestions = suggestions),
            searchHistoryViewState = SearchHistoryViewState.History(history = suggestions),
            connectivityStatus = ConnectivityObserver.ConnectivityStatus.Available,
            onSelectedSuggestion = {},
            onSelectedHistory = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherContentWithSuggestionLoadingPreview() {
    val suggestions = listOf("Celje", "Laško", "ljubljana", "Koper")

    KaldiWeatherTheme {
        WeatherContent(
            viewState = WeatherViewState.Idle,
            suggestionViewState = SuggestionViewState.Suggestions(suggestions = suggestions),
            searchHistoryViewState = SearchHistoryViewState.History(history = suggestions),
            connectivityStatus = ConnectivityObserver.ConnectivityStatus.Available,
            onSelectedSuggestion = {},
            onSelectedHistory = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherContentWithSuggestionLoadingAndSearchHistoryPreview() {
    val suggestions = listOf("Celje", "Laško", "ljubljana", "Koper")

    KaldiWeatherTheme {
        WeatherContent(
            viewState = WeatherViewState.Idle,
            suggestionViewState = SuggestionViewState.Suggestions(suggestions = suggestions),
            searchHistoryViewState = SearchHistoryViewState.History(history = suggestions),
            connectivityStatus = ConnectivityObserver.ConnectivityStatus.Available,
            onSelectedSuggestion = {},
            onSelectedHistory = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherContentWithSuggestionLoadingAndSearchHistoryAndWeatherPreview() {
    val suggestions = listOf("Celje", "Laško", "ljubljana", "Koper")
    val forecast = Forecast(
        temperature = 10.233,
        temperatureMax = 23.1458,
        relativeHumidity = 45.68,
        feelsLikeTemperature = 19.5687
    )

    KaldiWeatherTheme {
        WeatherContent(
            viewState = WeatherViewState.Success(forecast = forecast),
            suggestionViewState = SuggestionViewState.Suggestions(suggestions = suggestions),
            searchHistoryViewState = SearchHistoryViewState.History(history = suggestions),
            connectivityStatus = ConnectivityObserver.ConnectivityStatus.Available,
            onSelectedSuggestion = {},
            onSelectedHistory = {}
        )
    }
}