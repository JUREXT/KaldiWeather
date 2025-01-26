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
import com.programming.kaldiweather.ui.domain.model.Weather
import com.programming.kaldiweather.ui.theme.KaldiWeatherTheme

@Composable
fun WeatherDetails(
    modifier: Modifier = Modifier,
    weather: Weather
) {
    val unitC = stringResource(id = R.string.temp_unit_celsius)

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
                text = weather.city, fontSize = 24.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = "${weather.maxtemp_c} $unitC",
                fontSize = 64.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = "${weather.mintemp_c} $unitC",
                fontSize = 64.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingTextPreview() {
    val weather = Weather(
        city = "Celje",
        date = "26.01.2025",
        maxtemp_c = 23.33,
        mintemp_c = 2.88
    )

    KaldiWeatherTheme {
        WeatherDetails(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            weather = weather
        )
    }
}