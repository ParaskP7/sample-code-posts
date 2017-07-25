# Sample Code: Posts
This repository contains sample code. Its purpose being, to quickly demonstrate Android and software development in general, clean code, best practices, testing and all those other must know goodies.

The below listed skills are the main focus:

1. Architectural Pattern
    1. [MVP](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) ```(Canonical code only, without any libraries)```
    2. [Mosby MVP](https://github.com/sockeqwe/mosby) ```(Helper MVP library)```
2. Libraries
    1. [Dagger 2](https://github.com/google/dagger) ```(A fast dependency injector for Android and Java)```
    2. [Butter Knife](https://github.com/JakeWharton/butterknife) ```(Bind Android views and callbacks to fields and methods)```
    3. [Retrolambda](https://github.com/evant/gradle-retrolambda) ```(A gradle plugin for getting java lambda support in java 6, 7 and android)```
    4. [RxJava 2](https://github.com/ReactiveX/RxJava) ```(RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM)```
    5. [Retrofit 2](https://github.com/square/retrofit) ```(Type-safe HTTP client for Android and Java by Square, Inc.)```
    6. [Realm](https://github.com/realm/realm-java) ```(Realm is a mobile database: a replacement for SQLite & ORMs)```
    7. [Picasso](https://github.com/square/picasso) ```(A powerful image downloading and caching library for Android)```
    8. [Timber](https://github.com/JakeWharton/timber) ```(A logger with a small, extensible API which provides utility on top of Android's normal Log class)```
3. Android Support
    1. [Swipe Refresh Layout](https://developer.android.com/reference/android/support/v4/widget/SwipeRefreshLayout.html) ```(The SwipeRefreshLayout should be used whenever the user can refresh the contents of a view via a vertical swipe gesture)```
    2. [Card View](https://developer.android.com/reference/android/support/v7/widget/CardView.html) ```(A FrameLayout with a rounded corner background and shadow)```
    3. [Recycler View](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html) ```(A flexible view for providing a limited window into a large data set)```
    4. [Shared Element Transition](https://developer.android.com/training/material/animations.html#Transitions) ```(Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements)```
    5. [Vector Drawables](https://developer.android.com/training/material/drawables.html#VectorDrawables) ```(In Android 5.0 (API Level 21) and above, you can define vector drawables, which scale without losing definition)```
4. Code Quality
    1. [Android Lint](https://developer.android.com/studio/write/lint.html) ```(The lint tool checks your Android project source files for potential bugs and optimization improvements for correctness, security, performance, usability, accessibility, and internationalization)```
    2. [Checkstyle](https://github.com/checkstyle/checkstyle) ```(Checkstyle is a development tool to help programmers write Java code that adheres to a coding standard. By default it supports the Google Java Style Guide and Sun Code Conventions, but is highly configurable. It can be invoked with an ANT task and a command line program.)```
    3. [PMD](https://pmd.github.io/) ```(PMD is a source code analyzer)```
    4. [Findbugs](http://findbugs.sourceforge.net/) ```(A program which uses static analysis to look for bugs in Java code)```
    5. [Jacoco](https://github.com/jacoco/jacoco) ```(JaCoCo - Java Code Coverage Library)```
5. Tests
    1. [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/) ```(Espresso to write concise, beautiful, and reliable Android UI tests)```
    2. [JUnit](https://github.com/junit-team/junit4) ```(A programmer-oriented testing framework for Java)```
    3. [Robolectric](https://github.com/robolectric/robolectric) ```(Android Unit Testing Framework)```
    4. [Mockito](https://github.com/mockito/mockito) ```(Most popular Mocking framework for unit tests written in Java)```
    5. [AssertJ](https://github.com/joel-costigliola/assertj-core) ```(AssertJ is a library providing easy to use rich typed assertions)```

# Usage
Use the below command to build the project in order to install it on an Android device for demonstration:

```
gradlew clean build -x check
```

Open an emulator or connect a physical device to experiment with the sample app, use the below command, which first uninstalls and then installs the sample app:

```
gradlew uninstallDebug | gradlew installDebug
```

Or faster yet and targeting a specific device (in our case an emulator)!

```
adb -s emulator-5554 uninstall io.petros.posts | adb -s emulator-5554 install app\build\outputs\apk\debug\app-debug.apk
```

![alt tag](https://github.com/ParaskP7/sample-code-posts/blob/master/demo.jpg)

Use this command in order to run the static code analysis for the project:

```
gradlew check -x test
```

Or even run the below commands to run a specific code quality tool in isolation:

```
gradlew lint
gradlew checkstyle
gradlew pmd
gradlew findbugs
```

Run the project unit tests using this command, Jacoco is included:

```
gradlew test
gradlew testDebugUnitTestCoverage
```

Open an emulator or connect a physical device to run the instrumentation tests using this command:

```
gradlew connectedAndroidTest
```

# PS
I also hope this project will help others to quick understand and grasp all the listed technologies...

**ENJOY YOU**
