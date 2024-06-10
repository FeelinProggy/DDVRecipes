package com.cpw253.ddvrecipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cpw253.ddvrecipes.databinding.RecipesItemBinding

class RecipesAdapter(private val onRecipeSelected: (Recipe) -> Unit) :
    RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    private var recipes = listOf<Recipe>()

    fun submitList(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount() = recipes.size

    inner class RecipeViewHolder(private val binding: RecipesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.recipe = recipe
            binding.root.setOnClickListener {
                onRecipeSelected(recipe)
            }
            binding.ingredientsList.text = recipe.ingredients.joinToString(separator = "\n") { it.name }
        }
    }
}
