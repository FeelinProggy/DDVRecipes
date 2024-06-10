package com.cpw253.ddvrecipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cpw253.ddvrecipes.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val recipesViewModel: RecipesViewModel by activityViewModels {
        RecipesViewModelFactory(RecipesRepository())
    }

    private lateinit var recipesAdapter: RecipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        val view = binding.root

        recipesAdapter = RecipesAdapter { selectedRecipe ->
            val action = RecipesFragmentDirections.actionRecipesFragmentToIngredientsFragment(selectedRecipe)
            findNavController().navigate(action)
        }

        binding.recipeRecycler.layoutManager = LinearLayoutManager(context)
        binding.recipeRecycler.adapter = recipesAdapter

        recipesViewModel.filteredRecipes.observe(viewLifecycleOwner, Observer { filteredList ->
            recipesAdapter.submitList(filteredList)
        })

        binding.stars.setOnCheckedChangeListener { _, checkedId ->
            val starLevel = when (checkedId) {
                R.id.oneStar -> "1"
                R.id.twoStar -> "2"
                R.id.threeStar -> "3"
                R.id.fourStar -> "4"
                R.id.fiveStar -> "5"
                else -> ""
            }
            recipesViewModel.setStarLevelFilter(starLevel)
        }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
