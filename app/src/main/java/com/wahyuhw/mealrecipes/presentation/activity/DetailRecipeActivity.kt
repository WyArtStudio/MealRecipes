package com.wahyuhw.mealrecipes.presentation.activity

import android.content.Context
import android.content.Intent
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
import com.wahyuhw.mealrecipes.utils.emptyString
import com.wahyuhw.mealrecipes.utils.getCompatDrawable
import com.wahyuhw.mealrecipes.utils.observe
import com.wahyuhw.mealrecipes.utils.orDefault
import com.wahyuhw.mealrecipes.utils.setHtmlText
import com.wahyuhw.mealrecipes.utils.showToast
import com.wahyuhw.mealrecipes.viewmodel.RecipeViewModel
import org.koin.android.ext.android.inject

class DetailRecipeActivity : BaseActivity<ActivityDetailRecipeBinding>() {
	companion object {
		fun start(context: Context, id: String = emptyString(), data: RecipeDetail = RecipeDetail()) {
			val intent = Intent(context, DetailRecipeActivity::class.java)
			intent.putExtra(EXTRA_ID, id)
			intent.putExtra(EXTRA_RECIPE, data)
			context.startActivity(intent)
		}
	}
	
	private val recipeAdapter by lazy { RecipeIngredientsAdapter() }
	private var isAlreadySaved = false
	private var recipeDetail: RecipeDetail = RecipeDetail()
	private var id = emptyString()
	private val viewModel: RecipeViewModel by inject()
	private val listIngredient: MutableList<RecipeIngredient> = mutableListOf()
	
	override fun getViewBinding(): ActivityDetailRecipeBinding {
		return ActivityDetailRecipeBinding.inflate(layoutInflater)
	}
	
	override fun setupIntent() {
		recipeDetail = intent.getParcelableExtra<RecipeDetail>(EXTRA_RECIPE).orDefault(RecipeDetail())
		id = intent.getStringExtra(EXTRA_ID).orEmpty()
	}
	
	override fun setupUI() {
		if (id.isEmpty()) loadRecipe(recipeDetail)
	}
	
	override fun setupAction() {
		binding.imgBack.setOnClickListener { navigateBack() }
	}
	
	override fun setupProcess() {
		if (id.isNotEmpty()) {
			viewModel.getRecipeDetailById(id.toInt())
			viewModel.getLocalRecipeDetailById(id.toInt())
		}
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
				val recipeDetail = it?.firstOrNull().orDefault(RecipeDetail())
				loadRecipe(recipeDetail)
			}
		)
		
		viewModel.localRecipeDetailByIdResult.observe(this,
			onLoading = {},
			onError = {},
			onSuccess = {
				setBookmark(it != null)
				recipeDetail = it.orDefault(RecipeDetail())
			}
		)
		
		viewModel.addRecipeResult.observe(this,
			onLoading = {
				showLoading("Sedang menyimpan resep...")
			},
			onError = {
				showErrorState("Error: $it")
			},
			onSuccess = {
				if (it == true) {
					showToast("Berhasil disimpan!")
					binding.imgBookmark.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_saved))
					isAlreadySaved = true
				} else showErrorState("Gagal menyimpan data!")
			}
		)
		
		viewModel.removeRecipeResult.observe(this,
			onLoading = {
				showLoading("Sedang menghapus resep...")
			},
			onError = {
				showErrorState("Error: $it")
			},
			onSuccess = {
				if (it == true) {
					showToast("Berhasil menghapus!")
					binding.imgBookmark.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_unsaved))
					isAlreadySaved = false
				} else showErrorState("Gagal menghapus data!")
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
			for (i in 1..10)
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
			
			recipeAdapter.submitList(listIngredient)
			tvTitle.text = strMeal
			tvAreaCategory.text = "$strArea ($strCategory)"
			tvProcedures.setHtmlText(strInstructions)
			videoPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
				override fun onReady(youTubePlayer: YouTubePlayer) {
					val videoId: String = strYoutube
					youTubePlayer.cueVideo(videoId, 0f)
				}
			})
		}
	}
	
	private fun setBookmark(isAlreadyBookmarked: Boolean) {
		isAlreadySaved = isAlreadyBookmarked
		binding.imgBookmark.setImageDrawable(getCompatDrawable(
			if (isAlreadySaved) R.drawable.ic_bookmark_saved
			else R.drawable.ic_bookmark_unsaved
		))
		
		binding.imgBookmark.setOnClickListener {
			if (isAlreadySaved) {
				viewModel.removeLocalRecipe(recipeDetail)
			} else {
				viewModel.addLocalRecipe(recipeDetail)
			}
		}
	}
}