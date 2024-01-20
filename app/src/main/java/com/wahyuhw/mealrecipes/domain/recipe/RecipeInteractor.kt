package com.wahyuhw.mealrecipes.domain.recipe

import com.wahyuhw.mealrecipes.data.recipe.RecipeRepository
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeArea
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeCategory
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeDetail
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeItem
import com.wahyuhw.mealrecipes.domain.recipe.model.toEntity
import com.wahyuhw.mealrecipes.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class RecipeInteractor(private val repository: RecipeRepository): RecipeUseCase {
	override suspend fun getRecipeByName(searchQuery: String): Flow<Resource<List<RecipeDetail>>> {
		return repository.getRecipeByName(searchQuery)
	}
	
	override suspend fun getRecipeAreas(): Flow<Resource<List<RecipeArea>>> {
		return repository.getRecipeAreas()
	}
	
	override suspend fun getRecipeCategories(): Flow<Resource<List<RecipeCategory>>> {
		return repository.getRecipeCategories()
	}
	
	override suspend fun getRecipeDetailById(recipeId: Int): Flow<Resource<List<RecipeDetail>>> {
		return repository.getRecipeDetailById(recipeId)
	}
	
	override suspend fun getRecipeByCategory(category: String): Flow<Resource<List<RecipeItem>>> {
		return repository.getRecipeByCategory(category)
	}
	
	override suspend fun getRecipeByArea(area: String): Flow<Resource<List<RecipeItem>>> {
		return repository.getRecipeByArea(area)
	}
	
	override fun getAllLocalRecipe(): List<RecipeDetail> {
		return repository.getAllLocalRecipe()
	}
	
	override fun getLocalRecipeDetailById(id: Int): RecipeDetail {
		return repository.getLocalRecipeDetailById(id)
	}
	
	override fun addLocalRecipe(data: RecipeDetail) {
		return repository.addLocalRecipe(data.toEntity())
	}
	
	override fun removeLocalRecipe(data: RecipeDetail) {
		return repository.removeLocalRecipe(data.toEntity())
	}
}