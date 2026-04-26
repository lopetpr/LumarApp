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

**Package layout** follows feature-first + layer separation:

```
com.example.lumarapp
├── presentation/               # App-level shell
│   ├── MainActivity.kt         # Entry point, hosts NavController + theme
│   ├── navigation/
│   │   ├── Graph.kt            # Route constants (ROOT, AUTH, ...)
│   │   └── graph/RootNavGraph  # Top-level NavHost, nests feature graphs
│   └── components/             # Shared UI components (DefaultTextField, theme)
│       └── ui/theme/           # Color, Theme, Type
└── auth/
    └── presentation/
        ├── navigation/
        │   ├── screen/AuthScreen.kt   # Sealed class of auth routes
        │   └── graph/AuthNavGraph.kt  # NavGraphBuilder extension for auth
        └── screens/login/
            ├── LoginScreen.kt         # Scaffold wrapper
            └── components/LoginContent.kt  # Actual UI
```

**Navigation pattern**: `RootNavGraph` owns the `NavHost`. Each feature exposes a `NavGraphBuilder` extension (`AuthNavGraph`, etc.) that is called inside `RootNavGraph`. Routes live in sealed classes per feature (e.g. `AuthScreen`); graph-level route IDs live in `Graph` object.

**Composable split**: Screen composable = Scaffold only. Content composable = actual layout + state. Pattern: `LoginScreen` → `LoginContent(paddingValues)`.

**Shared components**: `DefaultTextField` in `presentation/components/` wraps `OutlinedTextField` with icon + keyboard options. Use it for new form fields instead of raw `OutlinedTextField`.

## Adding a New Feature

1. Create `<feature>/presentation/navigation/screen/<Feature>Screen.kt` — sealed class with routes.
2. Create `<feature>/presentation/navigation/graph/<Feature>NavGraph.kt` — `NavGraphBuilder` extension.
3. Add graph route constant to `Graph.kt`.
4. Nest the new graph inside `RootNavGraph`.
