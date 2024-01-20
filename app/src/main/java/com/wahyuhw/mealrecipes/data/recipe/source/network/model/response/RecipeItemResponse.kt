package com.wahyuhw.mealrecipes.data.recipe.source.network.model.response

import com.google.gson.annotations.SerializedName

data class RecipeItemResponse(
    @SerializedName("strMeal")
    val strMeal: String? = null,
    @SerializedName("strMealThumb")
    val strMealThumb: String? = null,
    @SerializedName("idMeal")
    val idMeal: String? = null
)