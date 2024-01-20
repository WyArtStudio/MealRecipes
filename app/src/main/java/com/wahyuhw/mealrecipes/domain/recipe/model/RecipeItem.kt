package com.wahyuhw.mealrecipes.domain.recipe.model

import com.wahyuhw.mealrecipes.utils.emptyString

data class RecipeItem(
    val strMeal: String = emptyString(),
    val strMealThumb: String = emptyString(),
    val idMeal: String = emptyString()
)