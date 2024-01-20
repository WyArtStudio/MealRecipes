package com.wahyuhw.mealrecipes.data.recipe.source.network.model.response

import com.google.gson.annotations.SerializedName

data class RecipeCategoryResponse(
	@SerializedName("strCategory")
	val strCategory: String? = null
)
