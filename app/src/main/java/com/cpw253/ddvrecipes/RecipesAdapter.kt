package com.cpw253.ddvrecipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cpw253.ddvrecipes.databinding.RecipesItemBinding

class RecipesAdapter(private val clickListener: (Recipe) -> Unit) : ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe, clickListener)
    }

    inner class RecipeViewHolder(private val binding: RecipesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe, clickListener: (Recipe) -> Unit) {
            binding.recipe = recipe
            binding.root.setOnClickListener {
                clickListener(recipe)
            }
            binding.executePendingBindings()
        }
    }

    class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.name == newItem.name // Assuming name is unique
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}