# LaunchTracker SpaceX

LaunchTracker SpaceX is a modern Android application that provides real-time tracking and detailed information about SpaceX launches. Built with Jetpack Compose and following clean architecture principles, the app offers a responsive and adaptive UI that works seamlessly across different device sizes and orientations.

## Features

- Real-time SpaceX launch data fetched from the SpaceX API
- Adaptive UI that optimizes the experience for both phones and tablets
- Master-detail interface on large screens in landscape orientation
- Mission details including rocket information, launch sites, and dates
- Mission patch images and comprehensive mission descriptions
- Sorted launch history with the most recent launches first

## Technical Stack

- **UI Framework**: Jetpack Compose with Material 3 Design
- **Architecture**: MVVM with Clean Architecture
- **Dependency Injection**: Hilt
- **Networking**: Retrofit with OkHttp
- **Image Loading**: Coil
- **API**: SpaceX API v3
- **State Management**: Kotlin Flow
- **Concurrency**: Coroutines

## Architecture

The application follows clean architecture principles with clear separation of concerns:

- **Model**: Contains data classes, repository implementations, and API services
- **ViewModel**: Manages UI state and business logic
- **View**: Compose UI components with responsive design

## Requirements

- Android Studio Arctic Fox or newer
- Minimum SDK: 21
- Target SDK: 34
- Kotlin 1.9.0 or newer

## Setup

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the application on an emulator or physical device

## UI Adaptations

The app provides different layouts based on device characteristics:

- **Small Devices (Phones)**:
  - Sequential navigation between launch list and details
  - Full-screen details view
  
- **Large Devices (Tablets)**:
  - Portrait: Sequential navigation similar to phones
  - Landscape: Split-screen master-detail view

## Dependencies

```gradle
dependencies {
    // Core Android
    implementation 'androidx.core:core-ktx:1.12.0'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.7.0'
    implementation 'androidx.activity:activity-compose:1.8.2'

    // Compose
    implementation platform('androidx.compose:compose-bom:2024.01.00')
    implementation 'androidx.compose.ui:ui'
    implementation 'androidx.compose.material3:material3'
    
    // Networking
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.12.0'
    
    // Dependency Injection
    implementation 'com.google.dagger:hilt-android:2.50'
    kapt 'com.google.dagger:hilt-compiler:2.50'
    
    // Image Loading
    implementation 'io.coil-kt:coil-compose:2.5.0'
}
```

## Acknowledgments

- [SpaceX API](https://github.com/r-spacex/SpaceX-API) - This project makes use of the open-source SpaceX REST API maintained by the r/SpaceX community. Special thanks to all the contributors who maintain this invaluable resource.



## Problem Statement

Create an application that queries the SpaceX Data API (https://api.spacexdata.com/v3/launches) to list all historical Launches and display extensive Launch details to the user.

Summary details displayed in the list should include:
	- Mission Name
	- Rocket Name
	- Launch Site Name
	- Date of Launch
	- Launch patch image, or default image when not provided by the API

Each summary item should be clickable. When clicked the full mission details provided by the API should be displayed.

Rotation (orientation change) should be supported on all form factors.
	- Portrait 
		- Launches shall be listed and ordered from newest to oldest.
		- when a Launch is selected the details screen shall be presented
	- Landscape
		- Large devices 
			- Shall display the Launch list and detail views in Master/Detail manner. 
			- When a Launch is selected the item shall remain highlighted in the list and the details view shall be populated with the selected Launch details.
		- Small devices
			- Shall display like portrait mode






