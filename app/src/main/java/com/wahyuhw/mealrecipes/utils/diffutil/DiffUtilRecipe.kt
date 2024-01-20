package com.wahyuhw.mealrecipes.utils.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeDetail
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeItem
import com.wahyuhw.mealrecipes.presentation.adapter.RecipeIngredient

class DiffUtilRecipeDetail : DiffUtil.ItemCallback<RecipeDetail>() {
    override fun areItemsTheSame(oldItem: RecipeDetail, newItem: RecipeDetail): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: RecipeDetail, newItem: RecipeDetail): Boolean {
        return oldItem == newItem
    }
}

class DiffUtilRecipeItem : DiffUtil.ItemCallback<RecipeItem>() {
    override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }
    
    override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return oldItem == newItem
    }
}

class DiffUtilRecipeIngredient : DiffUtil.ItemCallback<RecipeIngredient>() {
    override fun areItemsTheSame(oldItem: RecipeIngredient, newItem: RecipeIngredient): Boolean {
        return oldItem.ingredient == newItem.ingredient
    }
    
    override fun areContentsTheSame(oldItem: RecipeIngredient, newItem: RecipeIngredient): Boolean {
        return oldItem == newItem
    }
}