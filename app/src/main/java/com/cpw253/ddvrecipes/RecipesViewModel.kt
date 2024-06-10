package com.cpw253.ddvrecipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipesViewModel(private val recipesRepository: RecipesRepository) : ViewModel() {

    private val _filteredRecipes = MutableLiveData<List<Recipe>>()
    val filteredRecipes: LiveData<List<Recipe>> = _filteredRecipes

    private val _selectedRecipe = MutableLiveData<Recipe>()
    val selectedRecipe: LiveData<Recipe> = _selectedRecipe

    private val allRecipes: List<Recipe> = recipesRepository.getAllRecipes()

    private var currentStarLevel = ""
    private var currentMealType = "All"

    init {
        _filteredRecipes.value = allRecipes
    }

    fun setStarLevelFilter(starLevel: String) {
        currentStarLevel = starLevel
        applyFilters()
    }

    fun setMealTypeFilter(mealType: String) {
        currentMealType = mealType
        applyFilters()
    }

    private fun applyFilters() {
        val filteredList = allRecipes.filter { recipe ->
            (currentStarLevel.isEmpty() || recipe.starLevel.toString() == currentStarLevel) &&
                    (currentMealType.equals("All", ignoreCase = true) || recipe.meal.name.equals(currentMealType, ignoreCase = true))
        }
        _filteredRecipes.value = filteredList
    }

    fun selectRecipe(recipe: Recipe) {
        _selectedRecipe.value = recipe
    }
}

