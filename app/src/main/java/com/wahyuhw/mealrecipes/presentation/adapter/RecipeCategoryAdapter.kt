package com.wahyuhw.mealrecipes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahyuhw.mealrecipes.R
import com.wahyuhw.mealrecipes.base.BaseAdapter
import com.wahyuhw.mealrecipes.base.BaseViewHolder
import com.wahyuhw.mealrecipes.databinding.ItemRecipeCategoryBinding
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeItem
import com.wahyuhw.mealrecipes.utils.diffutil.DiffUtilRecipeItem
import com.wahyuhw.mealrecipes.utils.loadImageUrl

class RecipeCategoryAdapter(
	private val onClickListener: (String) -> Unit,
) : BaseAdapter<RecipeItem, ItemRecipeCategoryBinding, RecipeCategoryAdapter.RecipeItemVH>(
	DiffUtilRecipeItem()
) {
    inner class RecipeItemVH(override val binding: ItemRecipeCategoryBinding) : BaseViewHolder<RecipeItem>(binding) {

        override fun bind(data: RecipeItem) {
            with(binding) {
	            imgRecipe.loadImageUrl(
		            imgUrl = data.strMealThumb,
		            imgPlaceHolder = R.drawable.img_sample_recipe
	            )
	            
	            tvTitle.text = data.strMeal
				
                root.setOnClickListener {
                    onClickListener.invoke(data.idMeal)
                }
            }

        }
    }

    override fun createViewBinding(
	    inflater: LayoutInflater,
	    parent: ViewGroup
    ): ItemRecipeCategoryBinding {
        return ItemRecipeCategoryBinding.inflate(inflater, parent, false)
    }

    override fun createViewHolder(binding: ItemRecipeCategoryBinding): RecipeItemVH {
        return RecipeItemVH(binding)
    }
}