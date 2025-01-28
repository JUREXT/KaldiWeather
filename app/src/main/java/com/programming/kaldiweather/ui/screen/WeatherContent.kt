package com.programming.kaldiweather.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.programming.kaldiweather.R
import com.programming.kaldiweather.connection.ConnectivityObserver
import com.programming.kaldiweather.ui.component.AutoCompleteTextField
import com.programming.kaldiweather.ui.component.LoadingView
import com.programming.kaldiweather.ui.component.SearchHistoryCard
import com.programming.kaldiweather.ui.component.StatusView
import com.programming.kaldiweather.ui.component.WarningDialog
import com.programming.kaldiweather.ui.component.WeatherDetails
import com.programming.kaldiweather.ui.component.lifecycle.ComposableLifecycle
import com.programming.kaldiweather.ui.domain.model.Forecast
import com.programming.kaldiweather.ui.extension.findActivity
import com.programming.kaldiweather.ui.extension.isLocationEnabled
import com.programming.kaldiweather.ui.theme.KaldiWeatherTheme
import com.programming.kaldiweather.util.LocationPermissionHelper

@Composable
fun WeatherContent(
    viewState: WeatherViewState,
    suggestionViewState: SuggestionViewState,
    searchHistoryViewState: SearchHistoryViewState,
    connectivityStatus: ConnectivityObserver.ConnectivityStatus,
    onSelectedSuggestion: (String) -> Unit,
    onSelectedHistory: (String) -> Unit,
    onOpenSettings: () -> Unit,
    onGetLocation: () -> Unit
) {
    val context = LocalContext.current
    var warningDialogForCoarseLocationState by rememberSaveable {
        mutableStateOf(LocationPermissionHelper.Status.NOT_ACCEPTED)
    }
    var warningDialogForLocationEnabledState by rememberSaveable {
        mutableStateOf(LocationPermissionHelper.Status.NOT_ACCEPTED)
    }

    ComposableLifecycle { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            handleLocationState(
                context = context,
                onGetLocation = onGetLocation,
                onShowCoarseLocationWarning = {
                    warningDialogForCoarseLocationState = LocationPermissionHelper.Status.NOT_ACCEPTED
                },
                onShowLocationEnabledWarning = {
                    warningDialogForLocationEnabledState = LocationPermissionHelper.Status.NOT_ACCEPTED
                }
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (warningDialogForCoarseLocationState == LocationPermissionHelper.Status.NOT_ACCEPTED) {
            WarningDialog(
                title = stringResource(id = R.string.coarse_location_enable_location),
                message = stringResource(id = R.string.coarse_location_required_for_this_app),
                positiveButton = stringResource(id = R.string.coarse_location_enable_now),
                negativeButton = stringResource(id = R.string.coarse_location_continue_without),
                onPositiveClick = {
                    LocationPermissionHelper.requestLocationPermission(activity = context.findActivity())
                    warningDialogForCoarseLocationState = LocationPermissionHelper.Status.ACCEPTED
                },
                onNegativeClick = {
                    warningDialogForCoarseLocationState = LocationPermissionHelper.Status.NOT_NOW
                },
                onDismissRequest = {
                    // Ignore
                }
            )
        }

        if (warningDialogForLocationEnabledState == LocationPermissionHelper.Status.NOT_ACCEPTED) {
            WarningDialog(
                title = stringResource(id = R.string.location_enable_location),
                message = stringResource(id = R.string.location_required_for_this_app),
                positiveButton = stringResource(id = R.string.location_enable_now),
                negativeButton = stringResource(id = R.string.location_continue_without),
                onPositiveClick = {
                    warningDialogForLocationEnabledState = LocationPermissionHelper.Status.ACCEPTED
                    onOpenSettings()
                },
                onNegativeClick = {
                    warningDialogForLocationEnabledState = LocationPermissionHelper.Status.NOT_NOW
                },
                onDismissRequest = {
                    // Ignore
                }
            )
        }

        when (connectivityStatus) {
            ConnectivityObserver.ConnectivityStatus.Available -> {
                StatusView(
                    modifier = Modifier.fillMaxWidth(),
                    errorMessageRes = connectivityStatus.messageRes,
                    color = Color.Green
                )
            }

            ConnectivityObserver.ConnectivityStatus.Unavailable -> {
                StatusView(
                    modifier = Modifier.fillMaxWidth(),
                    errorMessageRes = connectivityStatus.messageRes,
                    color = Color.Red
                )
            }

            ConnectivityObserver.ConnectivityStatus.Losing -> {
                StatusView(
                    modifier = Modifier.fillMaxWidth(),
                    errorMessageRes = connectivityStatus.messageRes,
                    color = Color.Gray
                )
            }

            ConnectivityObserver.ConnectivityStatus.Lost -> {
                StatusView(
                    modifier = Modifier.fillMaxWidth(),
                    errorMessageRes = connectivityStatus.messageRes,
                    color = Color.Red
                )
            }
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
                modifier = Modifier.fillMaxSize()
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

private fun handleLocationState(
    context: Context,
    onGetLocation: () -> Unit,
    onShowCoarseLocationWarning: () -> Unit,
    onShowLocationEnabledWarning: () -> Unit
) {
    if (!context.isLocationEnabled()) {
        onShowLocationEnabledWarning()
    } else if (!LocationPermissionHelper.isLocationPermissionGranted(context)) {
        onShowCoarseLocationWarning()
    } else {
        onGetLocation()
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
            onSelectedHistory = {},
            onOpenSettings = {},
            onGetLocation = {}
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
            onSelectedHistory = {},
            onOpenSettings = {},
            onGetLocation = {}
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
            onSelectedHistory = {},
            onOpenSettings = {},
            onGetLocation = {}
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
            onSelectedHistory = {},
            onOpenSettings = {},
            onGetLocation = {}
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
            onSelectedHistory = {},
            onOpenSettings = {},
            onGetLocation = {}
        )
    }
}