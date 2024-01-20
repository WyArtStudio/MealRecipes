package com.wahyuhw.mealrecipes.data.recipe.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.wahyuhw.mealrecipes.data.recipe.source.local.entity.RecipeDetailEntity

@Dao
interface RecipeDao {
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(recipeDetailEntity: RecipeDetailEntity)
	
	@Update
	fun update(recipeDetailEntity: RecipeDetailEntity)
	
	@Delete
	fun delete(recipeDetailEntity: RecipeDetailEntity)
	
	@Query("DELETE from recipe_table")
	fun clear()
	
	@Query("SELECT * from recipe_table WHERE idMeal = :id")
	fun getById(id: Int): RecipeDetailEntity?
	
	@Query("SELECT * from recipe_table WHERE strMeal= :title")
	fun getByName(title: String): RecipeDetailEntity?
	
	@Query("SELECT * from recipe_table ORDER BY idMeal ASC")
	fun getAllMeal(): MutableList<RecipeDetailEntity>
}