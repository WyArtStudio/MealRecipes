package com.wahyuhw.mealrecipes.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahyuhw.mealrecipes.R
import com.wahyuhw.mealrecipes.base.BaseAdapter
import com.wahyuhw.mealrecipes.base.BaseViewHolder
import com.wahyuhw.mealrecipes.databinding.ItemRecipeAreaBinding
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeItem
import com.wahyuhw.mealrecipes.utils.diffutil.DiffUtilRecipeItem
import com.wahyuhw.mealrecipes.utils.loadImageUrl

class RecipeAreaAdapter(
	private val onClickListener: (String, String) -> Unit,
) : BaseAdapter<RecipeItem, ItemRecipeAreaBinding, RecipeAreaAdapter.RecipeItemVH>(
	DiffUtilRecipeItem()
) {
    inner class RecipeItemVH(override val binding: ItemRecipeAreaBinding) : BaseViewHolder<RecipeItem>(binding) {

        override fun bind(data: RecipeItem) {
            with(binding) {
	            imgRecipe.loadImageUrl(
		            imgUrl = data.strMealThumb,
		            imgPlaceHolder = R.drawable.img_sample_recipe
	            )
	            
	            tvTitle.text = data.strMeal
				
                root.setOnClickListener {
                    onClickListener.invoke(data.idMeal, data.strMeal)
                }
            }

        }
    }

    override fun createViewBinding(
	    inflater: LayoutInflater,
	    parent: ViewGroup
    ): ItemRecipeAreaBinding {
        return ItemRecipeAreaBinding.inflate(inflater, parent, false)
    }

    override fun createViewHolder(binding: ItemRecipeAreaBinding): RecipeItemVH {
        return RecipeItemVH(binding)
    }
}