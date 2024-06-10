package com.cpw253.ddvrecipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.cpw253.ddvrecipes.databinding.FragmentIngredientsBinding

class IngredientsFragment : Fragment() {

    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    private val ingredientsViewModel: IngredientsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        val view = binding.root

        val navArgs = arguments?.let { IngredientsFragmentArgs.fromBundle(it) }
        navArgs?.recipe?.let { recipe ->
            ingredientsViewModel.setRecipe(recipe)
        }

        ingredientsViewModel.recipe.observe(viewLifecycleOwner, Observer { observedRecipe ->
            binding.recipe = observedRecipe
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
