# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Unit tests
./gradlew test

# Instrumented tests (requires device/emulator)
./gradlew connectedAndroidTest

# Single unit test class
./gradlew testDebugUnitTest --tests "com.example.lumarapp.ExampleUnitTest"

# Lint
./gradlew lintDebug
```

## Architecture

Single-module Android app. Kotlin + Jetpack Compose. Navigation via `androidx.navigation:navigation-compose`.

**Package layout** — feature-first + Clean Architecture layers:

```
com.example.lumarapp
├── MainActivity.kt
├── LumarApplication.kt
├── core/
│   ├── navigation/             # Graph.kt (route constants), RootNavGraph.kt
│   └── ui/
│       ├── components/         # DefaultTextField (shared form field wrapper)
│       └── theme/              # Color.kt, Theme.kt, Type.kt
├── di/                         # AppModule.kt, RepositoryModule.kt (Hilt)
└── auth/
    ├── data/
    │   ├── local/              # TokenManager (DataStore)
    │   ├── remote/             # AuthApiService, AuthInterceptor, LoginRequest, LoginResponse
    │   └── repository/         # AuthRepositoryImpl (maps DTO → domain model)
    ├── domain/
    │   ├── model/              # User (domain model — no DTO leakage)
    │   ├── repository/         # AuthRepository interface (returns User)
    │   └── usecase/            # LoginUseCase, CheckSessionUseCase, LogoutUseCase
    └── presentation/
        ├── navigation/         # AuthScreen (sealed routes), AuthNavGraph
        └── login/
            ├── LoginScreen.kt          # Scaffold + ViewModel + LaunchedEffect for nav
            ├── LoginViewModel.kt       # StateFlow-based ViewModel
            ├── LoginUiState.kt         # UI state data class
            └── components/
                └── LoginContent.kt     # Stateless UI — receives lambdas, no ViewModel dep
```

**Navigation pattern**: `RootNavGraph` owns the `NavHost`. Each feature exposes a `NavGraphBuilder` extension (`AuthNavGraph`, etc.) called inside `RootNavGraph`. Routes live in sealed classes per feature (`AuthScreen`); graph-level IDs live in `Graph`.

**Composable split**: Screen = Scaffold only → delegates to Content composable. Pattern: `LoginScreen` → `LoginContent(paddingValues)`.

**State management**: ViewModel + `StateFlow<UiState>`. Each screen has a `*ViewModel`, a `*UiState` data class, and collects state via `collectAsStateWithLifecycle()`.

**Clean Architecture flow**: `presentation` → `domain` (use cases + interfaces) → `data` (implementations). `domain` layer has zero Android dependencies.

**Composable split**: `LoginScreen` owns ViewModel + navigation side-effects. `LoginContent` is stateless — receives `state: LoginUiState` + lambdas only. No `hiltViewModel()` in Content.

**Shared components**: Use `DefaultTextField` from `core/ui/components/` for form fields instead of raw `OutlinedTextField`.

**Theme colors** (use these, don't hardcode hex):
- `Blues` — primary blue `#1F51DC`
- `Gray800 / Gray500 / Gray300` — text and border grays
- `Purple40` — legacy default, prefer `Blues` for new UI

**App language**: UI strings are in Spanish.

## Adding a New Feature

1. Create `<feature>/presentation/navigation/screen/<Feature>Screen.kt` — sealed class with routes.
2. Create `<feature>/presentation/navigation/graph/<Feature>NavGraph.kt` — `NavGraphBuilder` extension.
3. Add graph route constant to `Graph.kt`.
4. Nest the new graph inside `RootNavGraph`.
