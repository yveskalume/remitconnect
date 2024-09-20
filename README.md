<h1 align="center">RemitConnect</h1><br>
<p align="center">  
<b>RemitConnect</b> is a remittance app that allows users to send money seamlessly from Europe to Africa.
</p>
<br>

<p align="center">
  <a href="https://github.com/yveskalume/remitconnect/actions/workflows/Build.yaml"><img alt="Build Status" src="https://github.com/yveskalume/remitconnect/actions/workflows/Build.yaml/badge.svg"/></a>
</p>
## Preview

<img src="/preview/preview.gif" width="32%"/>

## Architecture

The RemitConnect app employs a multimodule architecture, which facilitates better organization of
code and promotes separation of concerns. By dividing the application into distinct modules, we can
enhance maintainability, scalability, and reusability. Each module can be developed, tested, and
maintained independently, allowing for a more streamlined development process and reducing the risk
of introducing bugs in unrelated areas of the codebase.

<img src="/preview/architecture.png"/>

### Layered Architecture

The app is structured into several layers, each serving a specific purpose:

1. **Presentation Layer**: This layer is responsible for the user interface and user interaction. It
   consists of UI components that display data to the user and handle user inputs. The presentation
   layer is implemented using Jetpack Compose, ensuring a modern and responsive UI.

2. **Domain Layer**: This layer contains the business logic and application rules. The domain
   layer is designed to be independent of external libraries.

3. **Data Layer**: This layer handles data management, including fetching data from APIs, caching,
   and data persistence. It communicates with remote data sources and provides the necessary data to
   the domain layer.

#### Dependency Inversion

To ensure a robust and flexible architecture, we apply dependency inversion between the data and
domain layers. The domain layer defines interfaces that the data layer implements, allowing for
easier testing and decoupling of modules. This approach enables the app to evolve independently,
making it easier to introduce new features, switch data sources, or modify existing ones without
impacting the core business logic.

### Feature Modularization

The app is further organized into feature modules, each encapsulating specific functionalities. This
modularization allows for clear boundaries between features, making it easier to manage dependencies
and facilitate collaboration among team members. The key feature modules in the app include:

### Modules

| Module                   | Description                                                                                                                                                                                  | Dependencies                                                              |
|--------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **`:app`**               | The main application module that serves as the entry point for the app, orchestrating the various feature modules and core components.                                                       | `:feature:home`, `:feature:sendmoney`, `:core:designsystem`, `:core:data` |
| **`:feature:home`**      | This module handles the home screen functionality, allowing users to navigate and access key features of the app.                                                                            | `:core:designsystem`, `:core:data`                                        |
| **`:feature:sendmoney`** | This module manages the "Send Money" flow, guiding users through the process of sending money to recipients.                                                                                 | `:core:designsystem`, `:core:data`                                        |
| **`:core:designsystem`** | This core module contains reusable design components and styles, adhering to Material Design guidelines. It is utilized by both feature modules.                                             |                                                                           |
| **`:core:data`**         | This module manages data-related operations, including API calls and data persistence. It provides data to the `:feature:sendmoney` module and interacts with the `:core:domain` module.     | `:core:domain`                                                            |
| **`:core:domain`**       | This module contains the domain layer, including business models and use cases. It serves as the foundation for the data flow and logic shared between the `:core:data` and feature modules. |                                                                           |

**_This architecture not only supports a clean and maintainable codebase but also lays a solid
foundation for future enhancements and feature additions._**

## Challenge and difficulties encountered

- The provided Figma UI did not align with the Material You design system.
  Integrating it required careful customization of Material 3 components to ensure all UI elements
  were consistent across the app. This involved reworking styles, colors, and layouts to adhere to
  both the design specifications and Material Design Theming.

- There was an inconsistency in the API endpoint regarding country
  naming conventions. For example, the user endpoint referred to "Maroc," while the country name was
  listed as "Morocco." This caused errors when attempting to send money to the recipient
  **"Bilal Dahlab"**. While it was possible to handle this directly in the app code, that would not
  be an efficient solution. Ensuring that data is consistent across the server is the most suitable
  approach for long-term stability and reliability of the application.

- Fetching and displaying real-time currency conversion rates presented
  challenges, particularly in managing network latency and ensuring data consistency.

## Tech stack & Open-source libraries

The RemitConnect app is built using the following technologies and libraries:

### Core Technologies

- **Kotlin**: The primary programming language for Android development.
- **Android Studio**: IDE used for building the application.
- **Jetpack Compose**: A modern toolkit for building native UI in Android.

### Libraries and Frameworks

- **AndroidX Libraries**:
    - `androidx.core:core-ktx` - Core extensions for Android.
    - `androidx.lifecycle:lifecycle-runtime-ktx` - Lifecycle-aware components.
    - `androidx.activity:activity-compose` - Compose integration for activities.
    - `androidx.navigation:navigation-compose` - Navigation component for Jetpack Compose.
    - `androidx.compose.material3:material3` - Material Design 3 components for Compose.

- **Ktor**: An asynchronous HTTP client for making API calls.
    - `io.ktor:ktor-client-core` - Core Ktor client functionality.
    - `io.ktor:ktor-client-okhttp` - OkHttp integration for Ktor.
    - `io.ktor:ktor-serialization-kotlinx-json` - JSON serialization for Ktor.

- **Hilt**: Dependency injection library for Android.
    - `com.google.dagger:hilt-android` - Core Hilt functionality.
    - `com.google.dagger:hilt-android-compiler` - Annotation processor for Hilt.

- **Kotlinx Serialization**: For JSON serialization and deserialization.
    - `org.jetbrains.kotlinx:kotlinx-serialization-json` - JSON format support.

- **Coroutines**: For asynchronous programming.
    - `org.jetbrains.kotlinx:kotlinx-coroutines-core` - Core coroutine functionality.
    - `org.jetbrains.kotlinx:kotlinx-coroutines-test` - Testing support for coroutines.

- **Testing Libraries**:
    - `junit:junit` - Unit testing framework.
    - `io.mockk:mockk` - Mocking library for unit tests.
    - `org.robolectric:robolectric` - Unit testing framework for Android.