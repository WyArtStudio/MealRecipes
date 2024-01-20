package com.wahyuhw.mealrecipes.data.recipe.source.network

import com.wahyuhw.mealrecipes.base.BaseResponse
import com.wahyuhw.mealrecipes.data.recipe.source.network.model.response.RecipeAreaResponse
import com.wahyuhw.mealrecipes.data.recipe.source.network.model.response.RecipeCategoryResponse
import com.wahyuhw.mealrecipes.data.recipe.source.network.model.response.RecipeDetailResponse
import com.wahyuhw.mealrecipes.data.recipe.source.network.model.response.RecipeItemResponse
import retrofit2.Response

class RecipeApi(private val api: RecipeApiClient): RecipeApiClient {
	override suspend fun getRecipeByName(searchQuery: String): Response<BaseResponse<List<RecipeDetailResponse>>> {
		return api.getRecipeByName(searchQuery)
	}
	
	override suspend fun getRecipeAreas(area: String): Response<BaseResponse<List<RecipeAreaResponse>>> {
		return api.getRecipeAreas(area)
	}
	
	override suspend fun getRecipeCategories(category: String): Response<BaseResponse<List<RecipeCategoryResponse>>> {
		return api.getRecipeCategories()
	}
	
	override suspend fun getRecipeDetailById(recipeId: Int): Response<BaseResponse<List<RecipeDetailResponse>>> {
		return api.getRecipeDetailById(recipeId)
	}
	
	override suspend fun getRecipeByCategory(category: String): Response<BaseResponse<List<RecipeItemResponse>>> {
		return api.getRecipeByCategory(category)
	}
	
	override suspend fun getRecipeByArea(area: String): Response<BaseResponse<List<RecipeItemResponse>>> {
		return api.getRecipeByArea(area)
	}
}