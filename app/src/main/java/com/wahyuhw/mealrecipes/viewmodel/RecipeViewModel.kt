package com.wahyuhw.mealrecipes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wahyuhw.mealrecipes.base.BaseViewModel
import com.wahyuhw.mealrecipes.domain.recipe.RecipeUseCase
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeArea
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeCategory
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeDetail
import com.wahyuhw.mealrecipes.domain.recipe.model.RecipeItem
import com.wahyuhw.mealrecipes.domain.util.Resource
import com.wahyuhw.mealrecipes.utils.collectLocalResult
import com.wahyuhw.mealrecipes.utils.collectResult
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class RecipeViewModel(
	private val useCase: RecipeUseCase,
	disposable: CompositeDisposable
): BaseViewModel(disposable)  {
	private val _recipeByNameResult: MutableLiveData<Resource<List<RecipeDetail>>> = MutableLiveData()
	val recipeByNameResult: LiveData<Resource<List<RecipeDetail>>> get() = _recipeByNameResult
	
	private val _recipeAreasResult: MutableLiveData<Resource<List<RecipeArea>>> = MutableLiveData()
	val recipeAreasResult: LiveData<Resource<List<RecipeArea>>> get() = _recipeAreasResult
	
	private val _recipeCategoriesResult: MutableLiveData<Resource<List<RecipeCategory>>> = MutableLiveData()
	val recipeCategoriesResult: LiveData<Resource<List<RecipeCategory>>> get() = _recipeCategoriesResult
	
	private val _recipeDetailByIdResult: MutableLiveData<Resource<List<RecipeDetail>>> = MutableLiveData()
	val recipeDetailByIdResult: LiveData<Resource<List<RecipeDetail>>> get() = _recipeDetailByIdResult
	
	private val _recipeByCategoryResult: MutableLiveData<Resource<List<RecipeItem>>> = MutableLiveData()
	val recipeByCategoryResult: LiveData<Resource<List<RecipeItem>>> get() = _recipeByCategoryResult
	
	private val _recipeByAreaResult: MutableLiveData<Resource<List<RecipeItem>>> = MutableLiveData()
	val recipeByAreaResult: LiveData<Resource<List<RecipeItem>>> get() = _recipeByAreaResult
	
	private val _allLocalRecipeResult: MutableLiveData<Resource<List<RecipeDetail>>> = MutableLiveData()
	val allLocalRecipeResult: LiveData<Resource<List<RecipeDetail>>> get() = _allLocalRecipeResult
	
	private val _localRecipeDetailByIdResult: MutableLiveData<Resource<RecipeDetail>> = MutableLiveData()
	val localRecipeDetailByIdResult: LiveData<Resource<RecipeDetail>> get() = _localRecipeDetailByIdResult
	
	private val _addRecipeResult: MutableLiveData<Resource<Boolean>> = MutableLiveData()
	val addRecipeResult: LiveData<Resource<Boolean>> get() = _addRecipeResult
	
	private val _removeRecipeResult: MutableLiveData<Resource<Boolean>> = MutableLiveData()
	val removeRecipeResult: LiveData<Resource<Boolean>> get() = _removeRecipeResult
	
	init {
		_recipeByNameResult.value = Resource.Default()
		_recipeAreasResult.value = Resource.Default()
		_recipeCategoriesResult.value = Resource.Default()
		_recipeDetailByIdResult.value = Resource.Default()
		_recipeByCategoryResult.value = Resource.Default()
		_recipeByAreaResult.value = Resource.Default()
		_allLocalRecipeResult.value = Resource.Default()
		_localRecipeDetailByIdResult.value = Resource.Default()
		_addRecipeResult.value = Resource.Default()
		_removeRecipeResult.value = Resource.Default()
	}
	
	fun getRecipeByName(searchQuery: String) {
		viewModelScope.launch {
			collectResult(_recipeByNameResult) {
				useCase.getRecipeByName(searchQuery)
			}
		}
	}
	
	fun getRecipeAreas() {
		viewModelScope.launch {
			collectResult(_recipeAreasResult) {
				useCase.getRecipeAreas()
			}
		}
	}
	
	fun getRecipeCategories() {
		viewModelScope.launch {
			collectResult(_recipeCategoriesResult) {
				useCase.getRecipeCategories()
			}
		}
	}
	
	fun getRecipeDetailById(recipeId: Int) {
		viewModelScope.launch {
			collectResult(_recipeDetailByIdResult) {
				useCase.getRecipeDetailById(recipeId)
			}
		}
	}
	
	fun getRecipeByCategory(category: String) {
		viewModelScope.launch {
			collectResult(_recipeByCategoryResult) {
				useCase.getRecipeByCategory(category)
			}
		}
	}
	
	fun getRecipeByArea(area: String) {
		viewModelScope.launch {
			collectResult(_recipeByAreaResult) {
				useCase.getRecipeByArea(area)
			}
		}
	}
	
	fun getAllLocalRecipe() {
		viewModelScope.launch {
			collectLocalResult(_allLocalRecipeResult) {
				useCase.getAllLocalRecipe()
			}
		}
	}
	
	fun getLocalRecipeDetailById(id: Int) {
		viewModelScope.launch {
			collectLocalResult(_localRecipeDetailByIdResult) {
				useCase.getLocalRecipeDetailById(id)
			}
		}
	}
	
	fun addLocalRecipe(data: RecipeDetail) {
		viewModelScope.launch {
			_addRecipeResult.value = Resource.Loading()
			try {
				useCase.addLocalRecipe(data)
				_addRecipeResult.value = Resource.Success(true)
			} catch (e: Exception) {
				_addRecipeResult.value = Resource.Error(999,e.message ?: "An error occurred")
			}
		}
	}
	
	fun removeLocalRecipe(data: RecipeDetail) {
		viewModelScope.launch {
			_removeRecipeResult.value = Resource.Loading()
			try {
				useCase.addLocalRecipe(data)
				_removeRecipeResult.value = Resource.Success(true)
			} catch (e: Exception) {
				_removeRecipeResult.value = Resource.Error(999,e.message ?: "An error occurred")
			}
		}
	}
}