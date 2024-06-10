package com.cpw253.ddvrecipes

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("ingredientsText")
fun setIngredientsText(view: TextView, ingredients: List<Ingredient>?) {
    view.text = ingredients?.joinToString(separator = "\n") { it.name } ?: ""
}