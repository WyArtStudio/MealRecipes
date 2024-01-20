package com.wahyuhw.mealrecipes.data.recipe.source.network

import com.wahyuhw.mealrecipes.base.BaseResponse
import com.wahyuhw.mealrecipes.data.recipe.source.network.model.response.RecipeAreaResponse
import com.wahyuhw.mealrecipes.data.recipe.source.network.model.response.RecipeCategoryResponse
import com.wahyuhw.mealrecipes.data.recipe.source.network.model.response.RecipeDetailResponse
import com.wahyuhw.mealrecipes.data.recipe.source.network.model.response.RecipeItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiClient {
	@GET("search.php")
	suspend fun getRecipeByName(
		@Query("s") searchQuery: String,
	): Response<BaseResponse<List<RecipeDetailResponse>>>
	
	@GET("list.php")
	suspend fun getRecipeAreas(
		@Query("a") area: String = "list",
	): Response<BaseResponse<List<RecipeAreaResponse>>>
	
	@GET("list.php")
	suspend fun getRecipeCategories(
		@Query("c") category: String = "list",
	): Response<BaseResponse<List<RecipeCategoryResponse>>>
	
	@GET("lookup.php")
	suspend fun getRecipeDetailById(
		@Query("i") recipeId: Int,
	): Response<BaseResponse<List<RecipeDetailResponse>>>
	
	@GET("filter.php")
	suspend fun getRecipeByCategory(
		@Query("c") category: String,
	): Response<BaseResponse<List<RecipeItemResponse>>>
	
	@GET("filter.php")
	suspend fun getRecipeByArea(
		@Query("a") area: String,
	): Response<BaseResponse<List<RecipeItemResponse>>>
}