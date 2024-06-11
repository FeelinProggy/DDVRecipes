package com.cpw253.ddvrecipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.databinding.DataBindingUtil
import com.cpw253.ddvrecipes.databinding.FragmentRecipesBinding
import com.cpw253.ddvrecipes.databinding.DialogRecipeIngredientsBinding

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val recipesViewModel: RecipesViewModel by activityViewModels {
        RecipesViewModelFactory(RecipesRepository())
    }

    private lateinit var recipesAdapter: RecipesAdapter
    private var selectedRecipe: Recipe? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipes, container, false)
        val view = binding.root

        // Set up the toolbar
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        // Set up the RecyclerView
        recipesAdapter = RecipesAdapter { recipe ->
            selectedRecipe = recipe
            showRecipeDialog()
        }
        binding.recipeRecycler.layoutManager = LinearLayoutManager(context)
        binding.recipeRecycler.adapter = recipesAdapter

        // Observe the filtered recipes
        recipesViewModel.filteredRecipes.observe(viewLifecycleOwner) { filteredList ->
            recipesAdapter.submitList(filteredList)
        }

        // Set up the star rating filter
        binding.stars.setOnCheckedChangeListener { _, checkedId ->
            val starLevel = when (checkedId) {
                R.id.oneStar -> 1
                R.id.twoStar -> 2
                R.id.threeStar -> 3
                R.id.fourStar -> 4
                R.id.fiveStar -> 5
                else -> 1
            }
            recipesViewModel.setStarLevelFilter(starLevel)
        }

        // Set up the meal type filter
        binding.mealType.setOnCheckedChangeListener { _, checkedId ->
            val mealType = when (checkedId) {
                R.id.radio_appetizer -> "APPETIZER"
                R.id.radio_entree -> "ENTREE"
                R.id.radio_dessert -> "DESSERT"
                else -> "All"
            }
            recipesViewModel.setMealTypeFilter(mealType)
        }

        return view
    }

    private fun showRecipeDialog() {
        selectedRecipe?.let { recipe ->
            val dialogBinding: DialogRecipeIngredientsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.dialog_recipe_ingredients, null, false
            )
            dialogBinding.recipe = recipe

            val dialog = AlertDialog.Builder(requireContext())
                .setView(dialogBinding.root)
                .setPositiveButton("OK", null)
                .create()

            dialog.setOnShowListener {
                val window = dialog.window
                window?.setLayout(
                    ViewGroup.LayoutParams.WRAP_CONTENT,  // Set width as needed
                    ViewGroup.LayoutParams.WRAP_CONTENT  // Wrap the content height
                )
            }

            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
