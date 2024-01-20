package com.wahyuhw.mealrecipes.domain.recipe.model

import com.google.gson.annotations.SerializedName
import com.wahyuhw.mealrecipes.utils.emptyString

data class RecipeCategory(
	val strCategory: String = emptyString()
)
