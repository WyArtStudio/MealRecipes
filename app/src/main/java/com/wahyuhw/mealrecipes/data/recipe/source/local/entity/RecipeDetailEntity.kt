package com.wahyuhw.mealrecipes.data.recipe.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipe_table")
data class RecipeDetailEntity(
	@PrimaryKey
	@ColumnInfo("idMeal")
	val idMeal: String,
	@ColumnInfo("strMeal")
	val strMeal: String?,
	@ColumnInfo("strDrinkAlternate")
	val strDrinkAlternate: String?,
	@ColumnInfo("strCategory")
	val strCategory: String?,
	@ColumnInfo("strArea")
	val strArea: String?,
	@ColumnInfo("strInstructions")
	val strInstructions: String?,
	@ColumnInfo("strMealThumb")
	val strMealThumb: String?,
	@ColumnInfo("strTags")
	val strTags: String?,
	@ColumnInfo("strYoutube")
	val strYoutube: String?,
	@ColumnInfo("strIngredient1")
	val strIngredient1: String?,
	@ColumnInfo("strIngredient2")
	val strIngredient2: String?,
	@ColumnInfo("strIngredient3")
	val strIngredient3: String?,
	@ColumnInfo("strIngredient4")
	val strIngredient4: String?,
	@ColumnInfo("strIngredient5")
	val strIngredient5: String?,
	@ColumnInfo("strIngredient6")
	val strIngredient6: String?,
	@ColumnInfo("strIngredient7")
	val strIngredient7: String?,
	@ColumnInfo("strIngredient8")
	val strIngredient8: String?,
	@ColumnInfo("strIngredient9")
	val strIngredient9: String?,
	@ColumnInfo("strIngredient10")
	val strIngredient10: String?,
	@ColumnInfo("strIngredient11")
	val strIngredient11: String?,
	@ColumnInfo("strIngredient12")
	val strIngredient12: String?,
	@ColumnInfo("strIngredient13")
	val strIngredient13: String?,
	@ColumnInfo("strIngredient14")
	val strIngredient14: String?,
	@ColumnInfo("strIngredient15")
	val strIngredient15: String?,
	@ColumnInfo("strIngredient16")
	val strIngredient16: String?,
	@ColumnInfo("strIngredient17")
	val strIngredient17: String?,
	@ColumnInfo("strIngredient18")
	val strIngredient18: String?,
	@ColumnInfo("strIngredient19")
	val strIngredient19: String?,
	@ColumnInfo("strIngredient20")
	val strIngredient20: String?,
	@ColumnInfo("strMeasure1")
	val strMeasure1: String?,
	@ColumnInfo("strMeasure2")
	val strMeasure2: String?,
	@ColumnInfo("strMeasure3")
	val strMeasure3: String?,
	@ColumnInfo("strMeasure4")
	val strMeasure4: String?,
	@ColumnInfo("strMeasure5")
	val strMeasure5: String?,
	@ColumnInfo("strMeasure6")
	val strMeasure6: String?,
	@ColumnInfo("strMeasure7")
	val strMeasure7: String?,
	@ColumnInfo("strMeasure8")
	val strMeasure8: String?,
	@ColumnInfo("strMeasure9")
	val strMeasure9: String?,
	@ColumnInfo("strMeasure10")
	val strMeasure10: String?,
	@ColumnInfo("strMeasure11")
	val strMeasure11: String?,
	@ColumnInfo("strMeasure12")
	val strMeasure12: String?,
	@ColumnInfo("strMeasure13")
	val strMeasure13: String?,
	@ColumnInfo("strMeasure14")
	val strMeasure14: String?,
	@ColumnInfo("strMeasure15")
	val strMeasure15: String?,
	@ColumnInfo("strMeasure16")
	val strMeasure16: String?,
	@ColumnInfo("strMeasure17")
	val strMeasure17: String?,
	@ColumnInfo("strMeasure18")
	val strMeasure18: String?,
	@ColumnInfo("strMeasure19")
	val strMeasure19: String?,
	@ColumnInfo("strMeasure20")
	val strMeasure20: String?,
	@ColumnInfo("strSource")
	val strSource: String?,
	@ColumnInfo("strImageSource")
	val strImageSource: String?,
	@ColumnInfo("strCreativeCommonsConfirmed")
	val strCreativeCommonsConfirmed: String?,
	@ColumnInfo("dateModified")
	val dateModified: String?
): Parcelable
