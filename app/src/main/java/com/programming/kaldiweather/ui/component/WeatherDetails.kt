package com.programming.kaldiweather.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.programming.kaldiweather.R
import com.programming.kaldiweather.ui.domain.model.Forecast
import com.programming.kaldiweather.ui.domain.model.UnitType
import com.programming.kaldiweather.ui.extension.toHumanReadableValue
import com.programming.kaldiweather.ui.theme.KaldiWeatherTheme

@Composable
fun WeatherDetails(
    modifier: Modifier = Modifier,
    forecast: Forecast
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = stringResource(id = R.string.forecast_details_temperature),
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = forecast.temperature.toHumanReadableValue(unit = stringResource(id = UnitType.Temperature.unitRes)),
                fontSize = 55.sp
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = stringResource(id = R.string.forecast_details_temperature_max),
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = forecast.temperatureMax.toHumanReadableValue(unit = stringResource(id = UnitType.Temperature.unitRes)),
                fontSize = 55.sp
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = stringResource(id = R.string.forecast_details_temperature_min),
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = forecast.temperatureMin.toHumanReadableValue(unit = stringResource(id = UnitType.Temperature.unitRes)),
                fontSize = 55.sp
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = stringResource(id = R.string.forecast_details_temperature_feels_like),
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = forecast.feelsLikeTemperature.toHumanReadableValue(unit = stringResource(id = UnitType.Temperature.unitRes)),
                fontSize = 55.sp
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = stringResource(id = R.string.forecast_details_humidity),
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = forecast.relativeHumidity.toHumanReadableValue(unit = stringResource(id = UnitType.Humidity.unitRes)),
                fontSize = 55.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingTextPreview() {
    val forecast = Forecast(
        temperature = 10.233,
        temperatureMax = 23.1458,
        temperatureMin = 18.22,
        relativeHumidity = 45.68,
        feelsLikeTemperature = 19.5687
    )

    KaldiWeatherTheme {
        WeatherDetails(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            forecast = forecast
        )
    }
}