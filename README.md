# KaldiWeather

Projects uses:

- Kotlin, 
- Jetpack Compose,
- Kotlin-Coroutines and Flow,
- Hilt(DI),
- Retrofit with OkHttpClient, Moshi - Json, HttpLoggingInterceptor.
- ConnectivityObserver to check connection status.
- Location - FusedLocationProviderClient, tyep of coarse location, precision is not needed.
- Basic handling of permission.
- Following the pattern interface - implementation, using depency injection to provide objects.
- Extension function for easy converting of objects and code packeaging into small re-usable chunks.
- Life cycle observer to react to changes.
- Architecure is MVVM.
- Type safe navigation is used, where and object defines a route.

Screen for showing weather forecast is splitted: 

1. WeatherNavigation - used to define a route extension where backstack data can be handled and be included in NavHost.
2. WeatherScreen - serve as a connection point where states are collected with from view model or new chnages can be passed to viewmodel.
3. WeatherContentWeatherContent - Showing and reacting to state changes, uses callback to change state in viewmodel.

State object: Used to define possible UI presentation variants.

1. WeatherViewState - Used to show loading and forecast data.
2. SuggestionViewState - Used to show suggested city list.
3. SearchHistoryViewState - Used to show search history list.


Repository - interfaces:

1. SearchHistoryRepository - uses flow to live update UI and has a fixed capacity of 5 elements.
2. SuggestionRepository -Uses simple fun just to return predefined city list.
3. WeatherRepository - Is not defined by interface and serves as a bridge between rest api object and UI object, using extension function to map and manipulate data, basic error handling and using Dispatchers.IO.

Dependency injection: Created seperate modules to package connected object together.

Compose componets: Have a implementation and a preview with exampla data.

User interface was made on the fly and does not follow any design system.

App persmission are handled the basic way and are not stored, but are checked on every Lifecycle.Event.ON_RESUME.

App navigation is handled out side any reusable code.

App is a small demo example to showcase how code is connected, and has the possibility to be expanded easy by combining available repositories.