# Stickman World Android

A starter Android game project with a playable stickman, touch movement/jump controls, and an optional autonomous Shadow companion.

## GitHub Actions
The included workflow uses Gradle 8.9 directly, so a `gradlew` wrapper is not required on GitHub Actions. This avoids the `exit code 127` failure caused by a missing Gradle wrapper.

## Build
Run `gradle assembleDebug` in a machine with Android SDK/Gradle configured, or push to GitHub and use Actions. The workflow uploads `app-debug.apk` as an artifact.
