# WeatherApp

This is a sample app for weather forecast. User can select a city and save it locally to a list. Furthermore, there is also a delete action form the city list.

Application offers a 5 days forecast for a city and also the capability to view the hourly forecast for this city. Also, user can switch between hourly, 3 hour
and 6 hour forecast of the selected day.

From technical view, you can find:
- Mvvm
- Dependency injection (Koin)
- Room
- Retrofit
- Coroutines
- Glide

The base philosophy is to keep things simple. That means minimum amount of clicks from user. This is, for example, why you can see a simple dialog to add a city 
(the search starts after the third character).
