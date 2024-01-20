<h1 align="center"> Meal Recipes</h1> <br>
<a name="introduction"></a> 🤖 Introduction

Meal Recipe for Show meal recipe list with MVVM Clean Architecture

## <a name="features"></a> 🦾 Features

A few things you can do with Meal Recipes:

* Show meal recipe list item by category and area
* Search meal recipe by title
* Save recipe to bookmark


## <a name="installation"></a> 🚗 Installation

- Clone or download the repo
- Sync the gradle
- Rebuild Project
- Run in Android

## <a name="screenshot"></a> 📸 Screenshot
<img src='https://github.com/WyArtStudio/MealRecipes/blob/master/ss_meal%201.jpeg' width='200'>
<img src='https://github.com/WyArtStudio/MealRecipes/blob/master/ss_meal%202.jpeg' width='200'>
<img src='https://github.com/WyArtStudio/MealRecipes/blob/master/ss_meal%203.jpeg' width='200'>
<img src='https://github.com/WyArtStudio/MealRecipes/blob/master/ss%20meal%204.jpeg' width='200'>

## <a name="libraries"></a> 💡 Libraries

* Koin
* Retrofit
* OkHttpClient
* Material Design
* Some Kotlinx & Jetpack Components

## <a name="presentation-state-event"></a> 💨 Presentation State-Event
Im using Resource for handling many UI state 

State and Event
```kotlin
sealed class Resource<T>(
    open val data: T? = null,
    open val errorCode: Int? = null,
    open val errorMessage: String? = null,
) {
    data class Success<T>(
        override val data: T?,
    ) : Resource<T>(data)

    data class Error<T>(
        override val errorCode: Int,
        override val errorMessage: String,
    ) : Resource<T>(errorCode = errorCode, errorMessage = errorMessage)

    class Loading<T> : Resource<T>()
    class Default<T> : Resource<T>()
}
```
Reducing State and Event

```kotlin
fun <T> LiveData<Resource<T>>.observe(
	lifecycleOwner: LifecycleOwner,
	onLoading: () -> Unit,
	onSuccess: (items: T?) -> Unit,
	onError: (errorMessage: String) -> Unit,
) {
	observe(lifecycleOwner) {
		when (it) {
			is Resource.Default<T> -> {
				//Do nothing
			}
			is Resource.Loading<T> -> onLoading.invoke()
			is Resource.Success<T> -> onSuccess.invoke(it.data)
			else -> onError.invoke(it.errorMessage.orEmpty())
		}
	}
}
```

## <a name="project-structure"></a> 🏛 Project Structure

 - `base`
 - `data`
 - `di`
 - `domain`
 - `presentation`
 - `utils`
