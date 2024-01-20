package com.wahyuhw.mealrecipes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahyuhw.mealrecipes.base.BaseAdapter
import com.wahyuhw.mealrecipes.base.BaseViewHolder
import com.wahyuhw.mealrecipes.databinding.ItemIngredientsBinding
import com.wahyuhw.mealrecipes.utils.diffutil.DiffUtilRecipeIngredient
import com.wahyuhw.mealrecipes.utils.emptyString

data class RecipeIngredient(
	val ingredient: String = emptyString(),
	val pcs: String = emptyString()
)

class RecipeIngredientsAdapter() : BaseAdapter<RecipeIngredient, ItemIngredientsBinding, RecipeIngredientsAdapter.RecipeIngredientsVH>(
	DiffUtilRecipeIngredient()
) {
    inner class RecipeIngredientsVH(override val binding: ItemIngredientsBinding) : BaseViewHolder<RecipeIngredient>(binding) {

        override fun bind(data: RecipeIngredient) {
            with(binding) {
				tvTitle.text = data.ingredient
	            tvPcs.text = data.pcs
            }

        }
    }

    override fun createViewBinding(
	    inflater: LayoutInflater,
	    parent: ViewGroup
    ): ItemIngredientsBinding {
        return ItemIngredientsBinding.inflate(inflater, parent, false)
    }

    override fun createViewHolder(binding: ItemIngredientsBinding): RecipeIngredientsVH {
        return RecipeIngredientsVH(binding)
    }
}