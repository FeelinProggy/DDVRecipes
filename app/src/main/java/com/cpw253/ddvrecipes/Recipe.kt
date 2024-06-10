package com.cpw253.ddvrecipes

import java.io.Serializable
import kotlinx.serialization.Serializable as KSerializable

@KSerializable
data class Recipe(
    val meal: MealType,
    val name: String,
    val ingredients: List<Ingredient>
) : Serializable {
    val starLevel: Int
        get() = ingredients.size
}

@KSerializable
data class Ingredient(val name: String) : Serializable

@KSerializable
enum class MealType : Serializable {
    APPETIZER,
    ENTREE,
    DESSERT
}

class RecipesRepository {
    // This could be replaced with a database or network call to fetch recipes
    fun getAllRecipes(): List<Recipe> {
        return listOf(
            Recipe(MealType.APPETIZER, "EggPlant Puff", listOf(Ingredient("EggPlant"), Ingredient("Egg"), Ingredient("Cheese"))),
            Recipe(MealType.APPETIZER, "Bulgur Salad", listOf(Ingredient("Wheat"), Ingredient("Zucchini"), Ingredient("Tomato"), Ingredient("Basil"))),
            Recipe(MealType.ENTREE, "Mac & Cheese", listOf(Ingredient("Wheat"), Ingredient("Cheese"))),
            Recipe(MealType.ENTREE, "Sushi", listOf(Ingredient("Tuna"), Ingredient("Seaweed"), Ingredient("Rice"), Ingredient("Ginger"))),
            Recipe(MealType.ENTREE, "Lobster Roll", listOf(Ingredient("Lobster"), Ingredient("Wheat"), Ingredient("Lemon"), Ingredient("Butter"), Ingredient("Garlic"))),
            Recipe(MealType.DESSERT, "Caramel Apple", listOf(Ingredient("Sugar Cane"), Ingredient("Apple"))),
            Recipe(MealType.DESSERT, "Hot Cocoa", listOf(Ingredient("Sugar Cane"), Ingredient("Milk"), Ingredient("Cocoa Bean"))),
            Recipe(MealType.DESSERT, "Berry Salad", listOf(Ingredient("Raspberries"), Ingredient("Blueberries"), Ingredient("Gooseberries")))
        )
    }
}