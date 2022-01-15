# NativeMobileTest

## Clean Architecture
<div align="center">
    <img src="assets/clean-architecture.png"/>
</div>

- Presentation Layer, contains UI that are coordinated by ViewModels which execute the use cases.
- Domain Layer, completely pure kotlin module containing entities, use cases, & repository interfaces.
- Data Layer, contains repository implementation.

## Build With
- [Kotlin](https://kotlinlang.org/)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Retrofit](https://square.github.io/retrofit/)
- [OkHttp](https://square.github.io/okhttp/)
- [Gson](https://github.com/google/gson)
- [Shimmer](https://github.com/facebook/shimmer-android)