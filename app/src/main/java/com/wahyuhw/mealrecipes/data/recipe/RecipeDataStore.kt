package com.wahyuhw.mealrecipes.data.recipe

import com.wahyuhw.mealrecipes.data.recipe.source.local.RecipeDao
import com.wahyuhw.mealrecipes.data.recipe.source.local.entity.RecipeDetailEntity
import com.wahyuhw.mealrecipes.data.recipe.source.network.RecipeApiClient
import com.wahyuhw.mealrecipes.data.util.call
import com.wahyuhw.mealrecipes.data.util.mapToDomain
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeArea
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeCategory
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeDetail
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeItem
import com.wahyuhw.mealrecipes.domain.recipe.model.toDomain
import com.wahyuhw.mealrecipes.domain.util.Resource
import com.wahyuhw.mealrecipes.utils.orDefault
import kotlinx.coroutines.flow.Flow

class RecipeDataStore(
	private val webService: RecipeApiClient,
	private val dbService: RecipeDao
): RecipeRepository {
	override suspend fun getRecipeByName(searchQuery: String): Flow<Resource<List<RecipeDetail>>> {
		return webService.getRecipeByName(searchQuery).call().mapToDomain {
			response -> response.data?.map { it.toDomain() }.orEmpty()
		}
	}
	
	override suspend fun getRecipeAreas(): Flow<Resource<List<RecipeArea>>> {
		return webService.getRecipeAreas().call().mapToDomain {
				response -> response.data?.map { it.toDomain() }.orEmpty()
		}
	}
	
	override suspend fun getRecipeCategories(): Flow<Resource<List<RecipeCategory>>> {
		return webService.getRecipeCategories().call().mapToDomain {
				response -> response.data?.map { it.toDomain() }.orEmpty()
		}
	}
	
	override suspend fun getRecipeDetailById(recipeId: Int): Flow<Resource<List<RecipeDetail>>> {
		return webService.getRecipeDetailById(recipeId).call().mapToDomain {
				response -> response.data?.map { it.toDomain() }.orEmpty()
		}
	}
	
	override suspend fun getRecipeByCategory(category: String): Flow<Resource<List<RecipeItem>>> {
		return webService.getRecipeByCategory(category).call().mapToDomain {
				response -> response.data?.map { it.toDomain() }.orEmpty()
		}
	}
	
	override suspend fun getRecipeByArea(area: String): Flow<Resource<List<RecipeItem>>> {
		return webService.getRecipeByArea(area).call().mapToDomain {
				response -> response.data?.map { it.toDomain() }.orEmpty()
		}
	}
	
	override fun getAllLocalRecipe(): List<RecipeDetail> {
		return dbService.getAllMeal().map { it.toDomain() }
	}
	
	override fun getLocalRecipeDetailById(id: Int): RecipeDetail {
		return dbService.getById(id)?.toDomain().orDefault(RecipeDetail())
	}
	
	override fun addLocalRecipe(entity: RecipeDetailEntity) {
		return dbService.insert(entity)
	}
	
	override fun removeLocalRecipe(entity: RecipeDetailEntity) {
		return dbService.delete(entity)
	}
	
}