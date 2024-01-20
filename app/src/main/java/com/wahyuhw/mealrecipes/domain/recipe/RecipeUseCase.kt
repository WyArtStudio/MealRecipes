package com.wahyuhw.mealrecipes.domain.recipe

import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeArea
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeCategory
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeDetail
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeItem
import com.wahyuhw.mealrecipes.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface RecipeUseCase {
	suspend fun getRecipeByName(
		searchQuery: String,
	): Flow<Resource<List<RecipeDetail>>>
	
	suspend fun getRecipeAreas(): Flow<Resource<List<RecipeArea>>>
	
	suspend fun getRecipeCategories(): Flow<Resource<List<RecipeCategory>>>
	
	suspend fun getRecipeDetailById(
		recipeId: Int,
	): Flow<Resource<List<RecipeDetail>>>
	
	suspend fun getRecipeByCategory(
		category: String,
	): Flow<Resource<List<RecipeItem>>>
	
	suspend fun getRecipeByArea(
		area: String,
	): Flow<Resource<List<RecipeItem>>>
	
	fun getAllLocalRecipe(): List<RecipeDetail>
	
	fun getLocalRecipeDetailById(id: Int): RecipeDetail
	
	fun addLocalRecipe(data: RecipeDetail)
	
	fun removeLocalRecipe(data: RecipeDetail)
}