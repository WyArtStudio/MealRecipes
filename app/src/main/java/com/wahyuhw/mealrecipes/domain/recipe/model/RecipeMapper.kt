package com.wahyuhw.mealrecipes.domain.recipe.model

import com.wahyuhw.mealrecipes.data.recipe.source.local.entity.RecipeDetailEntity
import com.wahyuhw.mealrecipes.data.recipe.source.network.model.response.RecipeAreaResponse
import com.wahyuhw.mealrecipes.data.recipe.source.network.model.response.RecipeCategoryResponse
import com.wahyuhw.mealrecipes.data.recipe.source.network.model.response.RecipeDetailResponse
import com.wahyuhw.mealrecipes.data.recipe.source.network.model.response.RecipeItemResponse
import com.wahyuhw.mealrecipes.utils.orZero

fun RecipeDetailEntity.toDomain(): RecipeDetail {
	return RecipeDetail(
		idMeal = idMeal.orZero().toString(),
		strMeal = strMeal.orEmpty(),
		strDrinkAlternate = strDrinkAlternate.orEmpty(),
		strCategory = strCategory.orEmpty(),
		strArea = strArea.orEmpty(),
		strInstructions = strInstructions.orEmpty(),
		strMealThumb = strMealThumb.orEmpty(),
		strTags = strTags.orEmpty(),
		strYoutube = strYoutube.orEmpty(),
		strIngredient1 = strIngredient1.orEmpty(),
		strIngredient2 = strIngredient2.orEmpty(),
		strIngredient3 = strIngredient3.orEmpty(),
		strIngredient4 = strIngredient4.orEmpty(),
		strIngredient5 = strIngredient5.orEmpty(),
		strIngredient6 = strIngredient6.orEmpty(),
		strIngredient7 = strIngredient7.orEmpty(),
		strIngredient8 = strIngredient8.orEmpty(),
		strIngredient9 = strIngredient9.orEmpty(),
		strIngredient10 = strIngredient10.orEmpty(),
		strIngredient11 = strIngredient11.orEmpty(),
		strIngredient12 = strIngredient12.orEmpty(),
		strIngredient13 = strIngredient13.orEmpty(),
		strIngredient14 = strIngredient14.orEmpty(),
		strIngredient15 = strIngredient15.orEmpty(),
		strIngredient16 = strIngredient16.orEmpty(),
		strIngredient17 = strIngredient17.orEmpty(),
		strIngredient18 = strIngredient18.orEmpty(),
		strIngredient19 = strIngredient19.orEmpty(),
		strIngredient20 = strIngredient20.orEmpty(),
		strMeasure1 = strMeasure1.orEmpty(),
		strMeasure2 = strMeasure2.orEmpty(),
		strMeasure3 = strMeasure3.orEmpty(),
		strMeasure4 = strMeasure4.orEmpty(),
		strMeasure5 = strMeasure5.orEmpty(),
		strMeasure6 = strMeasure6.orEmpty(),
		strMeasure7 = strMeasure7.orEmpty(),
		strMeasure8 = strMeasure8.orEmpty(),
		strMeasure9 = strMeasure9.orEmpty(),
		strMeasure10 = strMeasure10.orEmpty(),
		strMeasure11 = strMeasure11.orEmpty(),
		strMeasure12 = strMeasure12.orEmpty(),
		strMeasure13 = strMeasure13.orEmpty(),
		strMeasure14 = strMeasure14.orEmpty(),
		strMeasure15 = strMeasure15.orEmpty(),
		strMeasure16 = strMeasure16.orEmpty(),
		strMeasure17 = strMeasure17.orEmpty(),
		strMeasure18 = strMeasure18.orEmpty(),
		strMeasure19 = strMeasure19.orEmpty(),
		strMeasure20 = strMeasure20.orEmpty(),
		strSource = strSource.orEmpty(),
		strImageSource = strImageSource.orEmpty(),
		strCreativeCommonsConfirmed = strCreativeCommonsConfirmed.orEmpty(),
		dateModified = dateModified.orEmpty()
	)
}

fun RecipeDetailResponse.toDomain(): RecipeDetail {
	return RecipeDetail(
		idMeal = idMeal.orEmpty(),
		strMeal = strMeal.orEmpty(),
		strDrinkAlternate = strDrinkAlternate.orEmpty(),
		strCategory = strCategory.orEmpty(),
		strArea = strArea.orEmpty(),
		strInstructions = strInstructions.orEmpty(),
		strMealThumb = strMealThumb.orEmpty(),
		strTags = strTags.orEmpty(),
		strYoutube = strYoutube.orEmpty(),
		strIngredient1 = strIngredient1.orEmpty(),
		strIngredient2 = strIngredient2.orEmpty(),
		strIngredient3 = strIngredient3.orEmpty(),
		strIngredient4 = strIngredient4.orEmpty(),
		strIngredient5 = strIngredient5.orEmpty(),
		strIngredient6 = strIngredient6.orEmpty(),
		strIngredient7 = strIngredient7.orEmpty(),
		strIngredient8 = strIngredient8.orEmpty(),
		strIngredient9 = strIngredient9.orEmpty(),
		strIngredient10 = strIngredient10.orEmpty(),
		strIngredient11 = strIngredient11.orEmpty(),
		strIngredient12 = strIngredient12.orEmpty(),
		strIngredient13 = strIngredient13.orEmpty(),
		strIngredient14 = strIngredient14.orEmpty(),
		strIngredient15 = strIngredient15.orEmpty(),
		strIngredient16 = strIngredient16.orEmpty(),
		strIngredient17 = strIngredient17.orEmpty(),
		strIngredient18 = strIngredient18.orEmpty(),
		strIngredient19 = strIngredient19.orEmpty(),
		strIngredient20 = strIngredient20.orEmpty(),
		strMeasure1 = strMeasure1.orEmpty(),
		strMeasure2 = strMeasure2.orEmpty(),
		strMeasure3 = strMeasure3.orEmpty(),
		strMeasure4 = strMeasure4.orEmpty(),
		strMeasure5 = strMeasure5.orEmpty(),
		strMeasure6 = strMeasure6.orEmpty(),
		strMeasure7 = strMeasure7.orEmpty(),
		strMeasure8 = strMeasure8.orEmpty(),
		strMeasure9 = strMeasure9.orEmpty(),
		strMeasure10 = strMeasure10.orEmpty(),
		strMeasure11 = strMeasure11.orEmpty(),
		strMeasure12 = strMeasure12.orEmpty(),
		strMeasure13 = strMeasure13.orEmpty(),
		strMeasure14 = strMeasure14.orEmpty(),
		strMeasure15 = strMeasure15.orEmpty(),
		strMeasure16 = strMeasure16.orEmpty(),
		strMeasure17 = strMeasure17.orEmpty(),
		strMeasure18 = strMeasure18.orEmpty(),
		strMeasure19 = strMeasure19.orEmpty(),
		strMeasure20 = strMeasure20.orEmpty(),
		strSource = strSource.orEmpty(),
		strImageSource = strImageSource.orEmpty(),
		strCreativeCommonsConfirmed = strCreativeCommonsConfirmed.orEmpty(),
		dateModified = dateModified.orEmpty()
	)
}

fun RecipeDetail.toEntity(): RecipeDetailEntity {
	return RecipeDetailEntity(
		idMeal = idMeal,
		strMeal = strMeal,
		strDrinkAlternate = strDrinkAlternate,
		strCategory = strCategory,
		strArea = strArea,
		strInstructions = strInstructions,
		strMealThumb = strMealThumb,
		strTags = strTags,
		strYoutube = strYoutube,
		strIngredient1 = strIngredient1,
		strIngredient2 = strIngredient2,
		strIngredient3 = strIngredient3,
		strIngredient4 = strIngredient4,
		strIngredient5 = strIngredient5,
		strIngredient6 = strIngredient6,
		strIngredient7 = strIngredient7,
		strIngredient8 = strIngredient8,
		strIngredient9 = strIngredient9,
		strIngredient10 = strIngredient10,
		strIngredient11 = strIngredient11,
		strIngredient12 = strIngredient12,
		strIngredient13 = strIngredient13,
		strIngredient14 = strIngredient14,
		strIngredient15 = strIngredient15,
		strIngredient16 = strIngredient16,
		strIngredient17 = strIngredient17,
		strIngredient18 = strIngredient18,
		strIngredient19 = strIngredient19,
		strIngredient20 = strIngredient20,
		strMeasure1 = strMeasure1,
		strMeasure2 = strMeasure2,
		strMeasure3 = strMeasure3,
		strMeasure4 = strMeasure4,
		strMeasure5 = strMeasure5,
		strMeasure6 = strMeasure6,
		strMeasure7 = strMeasure7,
		strMeasure8 = strMeasure8,
		strMeasure9 = strMeasure9,
		strMeasure10 = strMeasure10,
		strMeasure11 = strMeasure11,
		strMeasure12 = strMeasure12,
		strMeasure13 = strMeasure13,
		strMeasure14 = strMeasure14,
		strMeasure15 = strMeasure15,
		strMeasure16 = strMeasure16,
		strMeasure17 = strMeasure17,
		strMeasure18 = strMeasure18,
		strMeasure19 = strMeasure19,
		strMeasure20 = strMeasure20,
		strSource = strSource,
		strImageSource = strImageSource,
		strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
		dateModified = dateModified
	)
}

fun RecipeItemResponse.toDomain(): RecipeItem =
	RecipeItem(
		strMeal = strMeal.orEmpty(),
		strMealThumb = strMealThumb.orEmpty(),
		idMeal = idMeal.orEmpty()
	)

fun RecipeCategoryResponse.toDomain(): RecipeCategory =
	RecipeCategory(
		strCategory = strCategory.orEmpty()
	)

fun RecipeAreaResponse.toDomain(): RecipeArea =
	RecipeArea(
		strArea = strArea.orEmpty()
	)