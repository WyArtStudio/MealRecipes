package com.wahyuhw.mealrecipes.presentation.activity

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.wahyuhw.mealrecipes.R
import com.wahyuhw.mealrecipes.base.BaseActivity
import com.wahyuhw.mealrecipes.databinding.ActivityDetailRecipeBinding
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeDetail
import com.wahyuhw.mealrecipes.presentation.adapter.RecipeIngredient
import com.wahyuhw.mealrecipes.presentation.adapter.RecipeIngredientsAdapter
import com.wahyuhw.mealrecipes.utils.EXTRA_ID
import com.wahyuhw.mealrecipes.utils.EXTRA_RECIPE
import com.wahyuhw.mealrecipes.utils.EXTRA_TITLE
import com.wahyuhw.mealrecipes.utils.debug
import com.wahyuhw.mealrecipes.utils.emptyString
import com.wahyuhw.mealrecipes.utils.getCompatDrawable
import com.wahyuhw.mealrecipes.utils.observe
import com.wahyuhw.mealrecipes.utils.orDefault
import com.wahyuhw.mealrecipes.utils.setHtmlText
import com.wahyuhw.mealrecipes.utils.showToast
import com.wahyuhw.mealrecipes.viewmodel.LocalViewModel
import com.wahyuhw.mealrecipes.viewmodel.RecipeViewModel
import org.koin.android.ext.android.inject

class DetailRecipeActivity : BaseActivity<ActivityDetailRecipeBinding>() {
	companion object {
		fun start(context: Context, id: String = emptyString(), title: String = emptyString()) {
			val intent = Intent(context, DetailRecipeActivity::class.java)
			intent.putExtra(EXTRA_ID, id)
			intent.putExtra(EXTRA_TITLE, title)
			context.startActivity(intent)
		}
	}
	
	private val recipeAdapter by lazy { RecipeIngredientsAdapter() }
	private var isAlreadySaved = false
	private var recipeDetail: RecipeDetail = RecipeDetail()
	private var id = emptyString()
	private var title = emptyString()
	private val viewModel: RecipeViewModel by inject()
	private val localViewModel: LocalViewModel by inject()
	private val listIngredient: MutableList<RecipeIngredient> = mutableListOf()
	
	override fun getViewBinding(): ActivityDetailRecipeBinding {
		return ActivityDetailRecipeBinding.inflate(layoutInflater)
	}
	
	override fun setupIntent() {
		id = intent.getStringExtra(EXTRA_ID).orEmpty()
		title = intent.getStringExtra(EXTRA_TITLE).orEmpty()
	}
	
	override fun setupUI() {
		binding.rvIngredient.adapter = recipeAdapter
		checkBookmark(title)
	}
	
	override fun setupAction() {
		binding.imgBack.setOnClickListener { navigateBack() }
	}
	
	override fun setupProcess() {
		viewModel.getRecipeDetailById(id.toInt())
		viewModel.getLocalRecipeDetailById(id.toInt())
	}
	
	override fun setupObserver() {
		viewModel.recipeDetailByIdResult.observe(this,
			onLoading = {
				showLoading("Sedang memuat data...")
			},
			onError = {
				dismissLoading()
				showToast("Error: $it")
			},
			onSuccess = {
				dismissLoading()
				recipeDetail = it?.firstOrNull().orDefault(RecipeDetail())
				loadRecipe(recipeDetail)
			}
		)
	}
	
	private fun showErrorState(message: String) {
		showToast(message)
		dismissLoading()
	}
	
	private fun loadRecipe(recipeDetail: RecipeDetail) = with(binding) {
		viewModel.getLocalRecipeDetailById(recipeDetail.idMeal.toInt())
		recipeDetail.apply {
			if (strIngredient1.isNotEmpty()) listIngredient.add(
				RecipeIngredient(ingredient = strIngredient1, pcs = strMeasure1)
			)
			
			if (strIngredient2.isNotEmpty()) listIngredient.add(
				RecipeIngredient(ingredient = strIngredient2, pcs = strMeasure2)
			)
			
			if (strIngredient3.isNotEmpty()) listIngredient.add(
				RecipeIngredient(ingredient = strIngredient3, pcs = strMeasure3)
			)
			
			if (strIngredient4.isNotEmpty()) listIngredient.add(
				RecipeIngredient(ingredient = strIngredient4, pcs = strMeasure4)
			)
			
			if (strIngredient5.isNotEmpty()) listIngredient.add(
				RecipeIngredient(ingredient = strIngredient5, pcs = strMeasure5)
			)
			
			if (strIngredient6.isNotEmpty()) listIngredient.add(
				RecipeIngredient(ingredient = strIngredient6, pcs = strMeasure6)
			)
			
			if (strIngredient7.isNotEmpty()) listIngredient.add(
				RecipeIngredient(ingredient = strIngredient7, pcs = strMeasure7)
			)
			
			if (strIngredient8.isNotEmpty()) listIngredient.add(
				RecipeIngredient(ingredient = strIngredient8, pcs = strMeasure8)
			)
			
			if (strIngredient9.isNotEmpty()) listIngredient.add(
				RecipeIngredient(ingredient = strIngredient9, pcs = strMeasure9)
			)
			
			if (strIngredient10.isNotEmpty()) listIngredient.add(
				RecipeIngredient(ingredient = strIngredient10, pcs = strMeasure10)
			)
			debug { "List: $listIngredient" }
			recipeAdapter.submitList(listIngredient)
			
			tvTitle.text = strMeal
			tvAreaCategory.text = "$strArea ($strCategory)"
			tvProcedures.setHtmlText(strInstructions)
			videoPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
				override fun onReady(youTubePlayer: YouTubePlayer) {
					val videoId: String = strYoutube.substringAfterLast('=')
					youTubePlayer.cueVideo(videoId, 0f)
				}
			})
		}
	}
	
	private fun checkBookmark(recipeTitle: String) {
		localViewModel.getLocalRecipeByTitle(recipeTitle)
		localViewModel.localRecipe.observe(this) {
			if (it != null) {
				recipeDetail = it
				isAlreadySaved = true
				binding.imgBookmark.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_saved))
			} else {
				isAlreadySaved = false
				binding.imgBookmark.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_unsaved))
			}
		}
		
		binding.imgBookmark.setOnClickListener {
			if (isAlreadySaved) {
				localViewModel.removeRecipe(recipeDetail)
				showToast("Berhasil dihapus dari bookmark!")
				binding.imgBookmark.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_unsaved))
				isAlreadySaved = false
			} else {
				viewModel.addLocalRecipe(recipeDetail)
				showToast("Berhasil ditambahkan ke bookmark!")
				binding.imgBookmark.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_saved))
				isAlreadySaved = true
			}
		}
	}
}