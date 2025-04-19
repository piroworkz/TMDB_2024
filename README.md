
# TMDB2024

A Modern, Scalable Android Application

TMDB 2024 is a cutting-edge Android application built with Kotlin and Jetpack Compose, adhering to Clean Architecture and SOLID principles. This project showcases a robust approach to building maintainable, scalable, and testable Android applications using best-in-class practices.


Why Kotlin and Clean Architecture?


## Kotlin

A modern, concise language that compiles to Java bytecode. It offers features like null safety, higher-order functions, coroutines, and extension functions, leading to cleaner and more expressive code.
## Clean Architecture:

An architectural approach that separates concerns into distinct layers, promoting independence, testability, and maintainability. SOLID principles guide this separation of responsibilities.
        
### S.O.L.I.D.

- **Single Responsibility Principle:** A class should have one, and only one, reason to change.
- **Open-Closed Principle:** Entities should be open for extension, but closed for modification.
- **Liskov Substitution Principle:** Objects in a superclass should be replaceable with objects of its - subclasses without altering the correctness of the program.   
- **Interface Segregation Principle:** Clients should not be forced to depend on interfaces that they - do not use.
- **Dependency Inversion Principle:** Depend on abstractions, not concretions.


## Key Features

-    Modern UI with Jetpack Compose: Delivers a sleek and responsive user interface using Jetpack -Compose.
-    Efficient Data Serialization with Protobuf: Leverages Google Protocol Buffers for fast and -efficient data serialization and deserialization.
-    Secure Network Communication: Ensures secure data transfer with Retrofit and OkHttp.
-    Advanced State Management and Asynchronous Handling: Employs Kotlin Coroutines for seamless -asynchronous operations and data flow.
-    Clean Architecture Adherence: Strictly follows Clean Architecture principles for better code -organization and testability.
-    Comprehensive Testing: Includes a robust suite of unit and integration tests to guarantee code quality.(Instrumented testing comming soon)


## Benefits of This Approach

Cleaner, more maintainable code: Facilitates long-term code understanding and modification.
Enhanced testability: Enables writing more isolated and reliable unit tests.
Reduced coupling: Minimizes dependencies between different code parts.
Improved scalability: Allows for adding new features without significantly impacting existing cod.
Greater code reusability: Promotes the creation of reusable components.
## Getting Started
- ** Add your own TMDB api_key variable to your local.properties
### Prerequisites

- Android Studio Chipmunk | 2021.2.1 Patch 2 or higher
- JDK 1.8

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/ArchitectCoders2024.git
   ```

## Dependencies Used

### AndroidX
- `androidx.appcompat:appcompat` - Backwards compatibility for Android.
- `androidx.core:core-ktx` - Kotlin extensions for Android core libraries.
- `androidx.datastore:datastore-core` - Preferences storage using DataStore.
- `androidx.lifecycle:lifecycle-runtime-ktx` - Lifecycle management in Kotlin.
- `androidx.navigation:navigation-common-ktx` - Jetpack Navigation in Kotlin.
- `androidx.fragment:fragment-ktx` - Kotlin extensions for Fragment.
- `androidx.biometric:biometric` - Biometric authentication support.

### Kotlin
- `org.jetbrains.kotlinx:kotlinx-coroutines-core` - Support for asynchronous programming using coroutines.
- `org.jetbrains.kotlinx:kotlinx-serialization-json` - JSON serialization and deserialization library.

### Jetpack Compose
- `androidx.compose.ui:ui` - UI framework based on Composable functions.
- `androidx.compose.material3:material3` - Material Design 3 components.
- `androidx.activity:activity-compose` - Integration between Jetpack Compose and Android's Activity.
- `androidx.navigation:navigation-compose` - Navigation library for Jetpack Compose applications.
- `io.coil-kt:coil-compose` - Asynchronous image loading in Compose.

### Retrofit & OkHttp
- `com.squareup.retrofit2:retrofit` - HTTP client for interacting with REST APIs.
- `com.squareup.okhttp3:okhttp` - HTTP client for efficient network operations.
- `com.squareup.okhttp3:logging-interceptor` - Logging interceptor for OkHttp.

### Protobuf
- `com.google.protobuf:protobuf-javalite` - Protobuf implementation for Java Lite.
- `com.google.protobuf:protobuf-kotlin-lite` - Protobuf implementation for Kotlin Lite.

### Arrow
- `io.arrow-kt:arrow-core` - Functional programming library for Kotlin.

### Paging
- `androidx.paging:paging-compose` - Pagination support for Jetpack Compose.
- `androidx.paging:paging-runtime` - Pagination support for Android runtime.

### Google Services
- `com.google.android.gms:play-services-location` - Google Play Services Location APIs.
- `com.google.android.material:material` - Material Design components from Google.

### Hilt
- `com.google.dagger:hilt-android` - Dependency injection framework for Android.
- `androidx.hilt:hilt-navigation-compose` - Hilt integration for Jetpack Compose navigation.

### Piroworkz
- `com.piroworkz:compose-android-permissions` - Permission handling in Jetpack Compose.
- `com.piroworkz:versions-catalog` - A version catalog utility build-logic module.

### Unit Testing
- `junit:junit` - JUnit framework for unit testing.
- `org.mockito.kotlin:mockito-kotlin` - Mockito library for Kotlin.
- `app.cash.turbine:turbine` - Flow testing utilities.
- `com.google.truth:truth` - Assertion library for testing.

### Instrumentation Testing (Compose)
- `androidx.compose.ui:ui-test-junit4` - JUnit4 testing support for Jetpack Compose.
- `com.squareup.okhttp3:mockwebserver` - Mock server for OkHttp.
- `androidx.test.espresso:espresso-intents` - Intent interception for Espresso testing.


   [![Watch Video](https://img.youtube.com/vi/8OOVcnH2qE8/0.jpg)](https://www.youtube.com/watch?v=8OOVcnH2qE8)

