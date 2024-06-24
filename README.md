# Nutritective - Your Nutrition Detective
_Developed by: Kristina Koneva (student index number: 201513)_

## Overview
Nutritective is an Android native mobile app developed in Kotlin as the programming language of choice. The user interface is written in Jetpack Compose as a recommended modern UI toolkit which promises less code and accelerated intuitive development. The app consists of three layers: data, domain and ui and follows the MVVM pattern. The minimum supported SDK is API level 29 (Android 10) and the targeted SDK is API level 34 (Android 14). The app is fixed to portrait mode only. 

Nutritective offers a convenient way of obtaining nutrition data by scanning a food product's barcode, by inspecting an image (taken with the camera or chosen from gallery) or by entering text. Users are able to explore recipes in the app as well. Moreover, Nutritective allows user to select allergens which they want to be detected in the food porducts they scan or the food items they come across using the other ways of obtaining nutrition data. 

## Architecture
The app is divided into three layers: data, domain, UI.

### Data Layer
The data layer is the app data logic foundation where the direct communication with the sources resides. It is divided into remote and local sources. The data layer is the app data logic foundation where the direct communication with the sources resides.

### Domain Layer
The domain layer serves as a connection between the data and the domain layer. It consists of domain models, repositories and extension functions from transforming models from data to domain models and vice versa. The data models are transformed into domain models in this layer. The repositories provide an interface
for communicating with the data sources and their interface is used in the view models. They serve as an abstracted clean link to the data sources (without knowing their implementation and origin) in the UI layer.

### UI Layer
The UI layer is what the user sees on the screen. Nutritective's UI is implemented following the [MVVM pattern](https://infinum.com/handbook/android/project-architecture/mvvm) and [`StateFlow`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/) and [`Channel`](https://kotlinlang.org/docs/channels.html) are used for handling the state and event management logic.

## Demo
To be added.

## Integrated Sources
- [Open Food Facts API](https://openfoodfacts.github.io/openfoodfacts-server/api/) - for obtaining nutrition data for a certain product by passing its barcode
- [Calorie Ninjas API](https://calorieninjas.com/api) - for obtaining nutrition data from images and text input
- [Edamam API](https://www.edamam.com/) - for getting a recipe list for certain text input
- [Firebase Authentication](https://firebase.google.com/docs/auth/android/start) - for authenticating users
- [Cloud Firestore](https://firebase.google.com/docs/firestore/quickstart) - a database for storing the user's selected allergens

## Useful Libraries
- [Retrofit](https://square.github.io/retrofit/), [Okhttp](https://square.github.io/okhttp/), [KotlinX Serialization](https://github.com/Kotlin/kotlinx.serialization) - used for handling the networking logic
- [Hilt](https://dagger.dev/hilt/) - used for dependency injection
- [Compose BOM](https://developer.android.com/develop/ui/compose/bom) - used for implementing the UI and app navigation
- [ML Kit](https://developers.google.com/ml-kit/vision/barcode-scanning/android) - used for barcode scanning
- [Chucker](https://github.com/ChuckerTeam/chucker) - used for debugging API calls
- [Coil](https://github.com/coil-kt/coil) - used for image loading

## How to run the app?
Add the following content to your `local.properties` file:
```
CALORIE_NINJAS_API_KEY="<insert your Calorie Ninjas API key here>"
EDAMAM_APP_ID="<insert your Edamam app id here>"
EDAMAM_APP_KEY="<insert your Edamam app key here>"
```

