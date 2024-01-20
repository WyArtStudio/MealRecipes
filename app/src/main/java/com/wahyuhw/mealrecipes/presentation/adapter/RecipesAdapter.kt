package com.wahyuhw.mealrecipes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahyuhw.mealrecipes.R
import com.wahyuhw.mealrecipes.base.BaseAdapter
import com.wahyuhw.mealrecipes.base.BaseViewHolder
import com.wahyuhw.mealrecipes.databinding.ItemRecipeBinding
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeDetail
import com.wahyuhw.mealrecipes.utils.diffutil.DiffUtilRecipeDetail
import com.wahyuhw.mealrecipes.utils.loadImageUrl

class RecipesAdapter(
    private val onClickListener: (RecipeDetail) -> Unit,
) : BaseAdapter<RecipeDetail, ItemRecipeBinding, RecipesAdapter.RecipeVH>(DiffUtilRecipeDetail()) {
    inner class RecipeVH(override val binding: ItemRecipeBinding) : BaseViewHolder<RecipeDetail>(binding) {

        override fun bind(data: RecipeDetail) {
            with(binding) {
                imgRecipe.loadImageUrl(
                    imgUrl = data.strImageSource,
                    imgPlaceHolder = R.drawable.img_sample_recipe
                )
                
                tvTitle.text = data.strMeal
                
                root.setOnClickListener {
                    onClickListener.invoke(data)
                }
            }

        }
    }

    override fun createViewBinding(
	    inflater: LayoutInflater,
	    parent: ViewGroup
    ): ItemRecipeBinding {
        return ItemRecipeBinding.inflate(inflater, parent, false)
    }

    override fun createViewHolder(binding: ItemRecipeBinding): RecipeVH {
        return RecipeVH(binding)
    }
}