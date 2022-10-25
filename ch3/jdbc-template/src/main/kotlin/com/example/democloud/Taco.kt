package com.example.democloud

import java.util.*
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

class Taco {
    var id: String = ""

    @NotNull
    @Size(min=5, message="at least 5 characters")
    var name = "";

    var createdAt: Date = Date()

    @NotNull
    @Size(min=1, message="at least 1 ingredient")
    var ingredients = mutableListOf<Ingredient>();

    fun addIngredient(ingredient: Ingredient) {
        ingredients.add(ingredient)
    }
}