package com.wahyuhw.mealrecipes.presentation.activity

import android.content.Context
import android.content.Intent
import com.wahyuhw.mealrecipes.R
import com.wahyuhw.mealrecipes.base.BaseActivity
import com.wahyuhw.mealrecipes.databinding.ActivitySavedRecipesBinding
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeDetail
import com.wahyuhw.mealrecipes.presentation.adapter.RecipesAdapter
import com.wahyuhw.mealrecipes.utils.debug
import com.wahyuhw.mealrecipes.utils.observe
import com.wahyuhw.mealrecipes.utils.onTextSubmit
import com.wahyuhw.mealrecipes.utils.showContent
import com.wahyuhw.mealrecipes.utils.showEmptyList
import com.wahyuhw.mealrecipes.utils.showErrorState
import com.wahyuhw.mealrecipes.utils.showLoading
import com.wahyuhw.mealrecipes.viewmodel.LocalViewModel
import com.wahyuhw.mealrecipes.viewmodel.RecipeViewModel
import org.koin.android.ext.android.inject

class SavedRecipesActivity : BaseActivity<ActivitySavedRecipesBinding>() {
    companion object {
		fun start(context: Context) {
			val intent = Intent(context, SavedRecipesActivity::class.java)
			context.startActivity(intent)
		}
	}
	
	private val recipeAdapter by lazy { RecipesAdapter { recipe ->
		DetailRecipeActivity.start(this, id = recipe.idMeal, title = recipe.strMeal) }
	}
	private var listRecipe = emptyList<RecipeDetail>()
	private val localViewModel: LocalViewModel by inject()
	
	override fun getViewBinding(): ActivitySavedRecipesBinding {
		return ActivitySavedRecipesBinding.inflate(layoutInflater)
	}
	
	override fun setupIntent() {
	
	}
	
	override fun setupUI() {
		binding.rvRecipe.adapter = recipeAdapter
	}
	
	override fun setupAction() {
		binding.imgBack.setOnClickListener { navigateBack() }
		binding.edtSearch.onTextSubmit { title ->
			if (title.isNotEmpty()) {
				val filteredList = listRecipe.filter { data ->
					data.strMeal.contains(title, true)
				}
				setData(filteredList)
			} else setData(listRecipe)
		}
	}
	
	override fun setupProcess() {
		localViewModel.getLocalRecipe()
	}
	
	override fun setupObserver() {
		localViewModel.listLocalNews.observe(this) {
			listRecipe = it
			setData(listRecipe)
		}
	}
	
	private fun setData(listData: List<RecipeDetail>) {
		if (listData.isNotEmpty()) {
			recipeAdapter.submitList(listData)
			binding.msvSaved.showContent()
		} else
			binding.msvSaved.showEmptyList(message = "Belum ada resep disimpan!")
	}
	
	override fun onResume() {
		super.onResume()
		localViewModel.getLocalRecipe()
	}
}