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
            Recipe(MealType.DESSERT, "Berry Salad", listOf(Ingredient("Raspberries"), Ingredient("Blueberries"), Ingredient("Gooseberries"))),
            Recipe(MealType.DESSERT, "Blueberry Pie", listOf(Ingredient("Wheat"), Ingredient("Blueberry"), Ingredient("Butter"))),
            Recipe(MealType.DESSERT, "Boba Tea", listOf(Ingredient("Milk"), Ingredient("Sugarcane"))),
            Recipe(MealType.APPETIZER, "Bulgur Salad", listOf(Ingredient("Wheat"), Ingredient("Zucchini"), Ingredient("Tomato"), Ingredient("Basil"))),
            Recipe(MealType.DESSERT, "Caramel Apple", listOf(Ingredient("Sugar Cane"), Ingredient("Apple"))),
            Recipe(MealType.APPETIZER, "Crab Melts", listOf(Ingredient("Crab"), Ingredient("Cheese"))),
            Recipe(MealType.APPETIZER, "Crackers", listOf(Ingredient("Wheat"))),
            Recipe(MealType.APPETIZER, "Creamy Soup", listOf(Ingredient("Milk"), Ingredient("Potato"), Ingredient("Any Vegetable"), Ingredient("Any Spice"))),
            Recipe(MealType.APPETIZER, "EggPlant Puff", listOf(Ingredient("EggPlant"), Ingredient("Egg"), Ingredient("Cheese"))),
            Recipe(MealType.APPETIZER, "Hard-Boiled Eggs", listOf(Ingredient("Eggs"))),
            Recipe(MealType.DESSERT, "Hot Cocoa", listOf(Ingredient("Sugar Cane"), Ingredient("Milk"), Ingredient("Cocoa Bean"))),
            Recipe(MealType.ENTREE, "Lancetfish Paella", listOf(Ingredient("Any Seafood"), Ingredient("Lancetfish"), Ingredient("Shrimp"), Ingredient("Tomato"), Ingredient("Rice"))),
            Recipe(MealType.ENTREE, "Latkes", listOf(Ingredient("Onion"), Ingredient("Potato"), Ingredient("Canola"), Ingredient("Egg"))),
            Recipe(MealType.APPETIZER, "Latte", listOf(Ingredient("Coffee Bean"), Ingredient("Milk"))),
            Recipe(MealType.ENTREE, "Leek Soup", listOf(Ingredient("Leek"))),
            Recipe(MealType.ENTREE, "Lobster Roll", listOf(Ingredient("Lobster"), Ingredient("Wheat"), Ingredient("Lemon"), Ingredient("Butter"), Ingredient("Garlic"))),
            Recipe(MealType.ENTREE, "Mac & Cheese", listOf(Ingredient("Wheat"), Ingredient("Cheese"))),
            Recipe(MealType.DESSERT, "Mint Boba Tea", listOf(Ingredient("Mint"), Ingredient("Sugarcane"), Ingredient("Milk"))),
            Recipe(MealType.DESSERT, "Mint Candy", listOf(Ingredient("Mint"), Ingredient("Sugarcane"))),
            Recipe(MealType.ENTREE, "Sushi", listOf(Ingredient("Tuna"), Ingredient("Seaweed"), Ingredient("Rice"), Ingredient("Ginger")))
            )
    }
}