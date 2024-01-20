package com.wahyuhw.mealrecipes.data.recipe.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wahyuhw.mealrecipes.data.recipe.source.local.entity.RecipeDetailEntity

@Database(entities = [RecipeDetailEntity::class], version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase() {
	abstract fun recipeDao(): RecipeDao
	
	companion object {
		
		private var INSTANCE: RecipeDatabase? = null
		
		fun getAppDatabase(context: Context): RecipeDatabase {
			if (INSTANCE == null) {
				INSTANCE = Room
					.databaseBuilder(context.applicationContext, RecipeDatabase::class.java, "news_database")
					.fallbackToDestructiveMigration()
					.allowMainThreadQueries()
					.build()
			}
			return INSTANCE!!
		}
		
		fun destroyInstance() {
			INSTANCE = null
		}
	}
	
}