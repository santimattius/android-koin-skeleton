# Android Koin Skeleton

[![codecov](https://codecov.io/gh/santimattius/android-basic-skeleton/branch/master/graph/badge.svg?token=HNW9TXKMQU)](https://codecov.io/gh/santimattius/android-basic-skeleton)
![Quality Checks](https://github.com/santimattius/android-basic-skeleton/actions/workflows/main.yml/badge.svg)

A professional, production-ready Android skeleton project demonstrating modern development practices, Jetpack Compose, and Koin Dependency Injection. Designed for scalability, maintainability, and developer velocity.

---

## 🚀 Key Features

- **Modern UI Stack:** 100% [Jetpack Compose](https://developer.android.com/jetpack/compose) for a reactive and declarative UI.
- **Robust DI Architecture:** Powered by [Koin](https://insert-koin.io/) with full [KSP](https://kotlinlang.org/docs/ksp-overview.html) annotation support for compile-time safety.
- **Resilient Networking:** Type-safe API interactions using [Retrofit 3](https://square.github.io/retrofit/) and modern [OkHttp 5](https://square.github.io/okhttp/).
- **Advanced Performance Monitoring:** Integrated with [Automattic Measure Builds](https://github.com/automattic/android-measure-builds) to track build times and optimize developer workflows.
- **Strict Quality Control:** Pre-configured [Detekt](https://detekt.dev/) for static analysis and JaCoCo for comprehensive code coverage reporting.
- **Secure Secret Management:** Uses [Google Secrets Gradle Plugin](https://github.com/google/secrets-gradle-plugin) to keep sensitive keys out of version control.
- **Cutting-Edge Build Tooling:** Optimized for **Android Gradle Plugin 9.1** and **Gradle 9.1**, leveraging the latest Kotlin built-in integration.

---

## 🛠 Tech Stack

| Category | Technology |
| :--- | :--- |
| **Language** | [Kotlin](https://kotlinlang.org/) |
| **UI Framework** | [Jetpack Compose](https://developer.android.com/jetpack/compose) |
| **Dependency Injection** | [Koin](https://insert-koin.io/) (Core, Compose, Annotations) |
| **Networking** | [Retrofit](https://square.github.io/retrofit/), [OkHttp](https://square.github.io/okhttp/), [Gson](https://github.com/google/gson) |
| **Concurrency** | [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) |
| **Image Loading** | [Coil](https://coil-kt.github.io/coil/) |
| **Lifecycle** | [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) |
| **Static Analysis** | [Detekt](https://detekt.dev/) |
| **Build System** | Gradle (Kotlin DSL) + [Version Catalogs](https://developer.android.com/build/migrate-to-catalogs) |

---

## 🏁 Getting Started

### Prerequisites

- **Android Studio Jellyfish** | 2023.3.1 or higher.
- **JDK 17** (configured in your IDE and environment).

### Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/santimattius/android-koin-skeleton.git
    ```
2.  **Open in Android Studio:**
    Select the root directory and wait for the initial Gradle sync to complete.

### 🔑 Secure Configuration

This project strictly adheres to security best practices for API keys.

1.  Open or create `local.properties` in the project root.
2.  Add your sensitive data:
    ```properties
    apiKey="your_api_key_here"
    ```
3.  The plugin will automatically generate fields in `BuildConfig` for safe access within the app.

---

## 🧪 Quality & Verification

Maintain high code standards with the following commands:

### Run Full Check Suite
Executes unit tests and static analysis.
```bash
./gradlew check
```

### Static Analysis (Detekt)
```bash
./gradlew :app:detekt
```

### Code Coverage Reports
Reports are generated via JaCoCo for both debug and release variants.
```bash
# Debug Coverage Report
./gradlew :app:testDebugUnitTestCoverage

# Release Coverage Report
./gradlew :app:testReleaseUnitTestCoverage
```

---

## 🏗 CI/CD & GitHub Actions

The project includes a robust **GitHub Actions** pipeline (`.github/workflows/main.yml`) that automates:
- Dependency resolution and verification.
- Static analysis via Detekt.
- Unit testing with automated secret injection for the test environment.
- Code coverage uploading to Codecov.

---

## 📚 Resources & Variations

- **Migration Guide:** [AGP 9.1 Migration Details](docs/MIGRATION_GUIDE_AGP_9.md)
- **Firebase Integration:** Looking for Firebase/Crashlytics? Check the [`with_crashlytics`](https://github.com/santimattius/android-basic-skeleton/tree/with_crashlitycs) branch.

---

## 🤝 Contributing

We welcome contributions that improve the skeleton or add modern best practices. 
1. Fork the repo.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.
