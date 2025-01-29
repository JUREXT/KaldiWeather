package com.programming.kaldiweather.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.programming.kaldiweather.ui.extension.findActivity
import com.programming.kaldiweather.ui.screen.weatherScreen
import com.programming.kaldiweather.util.IntentUtils

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val activity = LocalContext.current.findActivity()

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = NavRoutes.WeatherRoute
    ) {
        weatherScreen(
            onExit = {
                activity.finish()
            },
            onOpenSettings = {
                IntentUtils.openSettings(activity = activity)
            }
        )
    }
}