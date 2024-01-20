package com.wahyuhw.mealrecipes.presentation.activity

import com.kennyc.view.MultiStateView
import com.wahyuhw.mealrecipes.R
import com.wahyuhw.mealrecipes.base.BaseActivity
import com.wahyuhw.mealrecipes.databinding.ActivityMainBinding
import com.wahyuhw.mealrecipes.presentation.adapter.RecipeAreaAdapter
import com.wahyuhw.mealrecipes.presentation.adapter.RecipeCategoryAdapter
import com.wahyuhw.mealrecipes.utils.DEFAULT_AREA
import com.wahyuhw.mealrecipes.utils.DEFAULT_CATEGORY
import com.wahyuhw.mealrecipes.utils.addListTab
import com.wahyuhw.mealrecipes.utils.gone
import com.wahyuhw.mealrecipes.utils.observe
import com.wahyuhw.mealrecipes.utils.onTabPositionSelected
import com.wahyuhw.mealrecipes.utils.showContent
import com.wahyuhw.mealrecipes.utils.showEmptyList
import com.wahyuhw.mealrecipes.utils.showErrorState
import com.wahyuhw.mealrecipes.utils.showLoading
import com.wahyuhw.mealrecipes.utils.visible
import com.wahyuhw.mealrecipes.viewmodel.RecipeViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>() {
	private val viewModel: RecipeViewModel by inject()
	private var selectedArea: String = DEFAULT_AREA
	private var selectedCategory: String = DEFAULT_CATEGORY
	private val recipeAreaAdapter by lazy { RecipeAreaAdapter { recipeId, recipeTitle ->
		DetailRecipeActivity.start(this, id = recipeId, title = recipeTitle)
	}}
	
	private val recipeCategoryAdapter by lazy { RecipeCategoryAdapter { recipeId, recipeTitle ->
		DetailRecipeActivity.start(this, id = recipeId, title = recipeTitle)
	}}
	
	override fun getViewBinding(): ActivityMainBinding {
		return ActivityMainBinding.inflate(layoutInflater)
	}
	
	override fun setupIntent() {
	
	}
	
	override fun setupUI() {
		binding.rvRecipeByArea.adapter = recipeAreaAdapter
		binding.rvRecipeByCategory.adapter = recipeCategoryAdapter
	}
	
	override fun setupAction() {
		binding.imgBookmark.setOnClickListener { SavedRecipesActivity.start(this) }
		
		binding.edtSearch.setOnClickListener { SearchActivity.start(this) }
		
		binding.tabCategory.onTabPositionSelected { category: String ->
			selectedCategory = category
			viewModel.getRecipeByCategory(selectedCategory)
		}
		
		binding.tabArea.onTabPositionSelected { area: String ->
			selectedArea = area
			viewModel.getRecipeByArea(selectedArea)
		}
	}
	
	override fun setupProcess() {
		viewModel.getRecipeAreas()
		viewModel.getRecipeCategories()
		viewModel.getRecipeByCategory(selectedCategory)
		viewModel.getRecipeByArea(selectedArea)
	}
	
	override fun setupObserver() {
		viewModel.recipeAreasResult.observe(this,
			onLoading = {
				binding.tabArea.gone()
			},
			onError = {},
			onSuccess = {
				it?.let { data -> binding.tabArea.addListTab(
					data.map { tab -> tab.strArea })
					binding.tabArea.visible()
				}
			}
		)
		
		viewModel.recipeCategoriesResult.observe(this,
			onLoading = {
				binding.tabCategory.gone()
			},
			onError = {},
			onSuccess = {
				it?.let { data -> binding.tabCategory.addListTab(
					data.map { tab -> tab.strCategory })
					binding.tabCategory.visible()
				}
			}
		)
		
		viewModel.recipeByAreaResult.observe(this,
			onLoading = {
				binding.msvRecipeArea.showLoading()
			},
			onError = {
				binding.msvRecipeArea.showErrorState(
					title = getString(R.string.label_error),
					message = it,
					imgResourceId = null,
					onRetry = {
						viewModel.getRecipeByArea(selectedArea)
					}
				)
			},
			onSuccess = {
				if (it?.isNotEmpty() == true) {
					recipeAreaAdapter.submitList(it)
					binding.msvRecipeArea.showContent()
				} else
					binding.msvRecipeArea.showEmptyState("Tidak ada resep di area tersebut.")
			}
		)
		
		viewModel.recipeByCategoryResult.observe(this,
			onLoading = {
				binding.msvRecipeCategory.showLoading()
			},
			onError = {
				binding.msvRecipeCategory.showErrorState(
					title = getString(R.string.label_error),
					message = it,
					imgResourceId = null,
					onRetry = {
						viewModel.getRecipeByArea(selectedArea)
					}
				)
			},
			onSuccess = {
				if (it?.isNotEmpty() == true) {
					recipeCategoryAdapter.submitList(it)
					binding.msvRecipeCategory.showContent()
				} else
					binding.msvRecipeCategory.showEmptyState("Tidak ada resep di kategori tersebut.")
			}
		)
	}
	
	private fun MultiStateView.showEmptyState(message: String) {
		this.showEmptyList(
			title = message
		)
	}
}