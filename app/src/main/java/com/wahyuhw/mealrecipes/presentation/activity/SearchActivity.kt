package com.wahyuhw.mealrecipes.presentation.activity

import android.content.Context
import android.content.Intent
import com.wahyuhw.mealrecipes.R
import com.wahyuhw.mealrecipes.base.BaseActivity
import com.wahyuhw.mealrecipes.databinding.ActivitySearchBinding
import com.wahyuhw.mealrecipes.presentation.adapter.RecipesAdapter
import com.wahyuhw.mealrecipes.utils.emptyString
import com.wahyuhw.mealrecipes.utils.observe
import com.wahyuhw.mealrecipes.utils.onTextSubmit
import com.wahyuhw.mealrecipes.utils.showContent
import com.wahyuhw.mealrecipes.utils.showEmptyList
import com.wahyuhw.mealrecipes.utils.showErrorState
import com.wahyuhw.mealrecipes.utils.showLoading
import com.wahyuhw.mealrecipes.viewmodel.RecipeViewModel
import org.koin.android.ext.android.inject

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    companion object {
		fun start(context: Context) {
			val intent = Intent(context, SearchActivity::class.java)
			context.startActivity(intent)
		}
	}
	
	private val recipeAdapter by lazy { RecipesAdapter { recipe ->
		DetailRecipeActivity.start(this, data = recipe) }
	}
	private val viewModel: RecipeViewModel by inject()
	private var query = emptyString()
	
	override fun getViewBinding(): ActivitySearchBinding {
		return ActivitySearchBinding.inflate(layoutInflater)
	}
	
	override fun setupIntent() {
	
	}
	
	override fun setupUI() {
		binding.rvRecipe.adapter = recipeAdapter
	}
	
	override fun setupAction() {
		binding.imgBack.setOnClickListener { navigateBack() }
		binding.edtSearch.onTextSubmit {
			query = it
			viewModel.getRecipeByName(query)
		}
	}
	
	override fun setupProcess() {}
	
	override fun setupObserver() {
		viewModel.recipeByNameResult.observe(this,
			onLoading = {
				binding.msvSearch.showLoading()
			},
			onError = {
				binding.msvSearch.showErrorState(
					title = getString(R.string.label_error),
					message = it,
					imgResourceId = null,
					onRetry = {
						viewModel.getRecipeByName(query)
					}
				)
			},
			onSuccess = {
				if (it?.isNotEmpty() == true) {
					recipeAdapter.submitList(it)
					binding.msvSearch.showContent()
				} else
					binding.msvSearch.showEmptyList(message = "Resep tidak ditemukan! Coba kata kunci lain?")
			}
		)
	}
}