package com.example.democloud

interface IngredientRepository {
    fun findAll():Iterable<Ingredient>

    fun findById(id: String): Ingredient?

    fun save(ingredient: Ingredient): Ingredient
}