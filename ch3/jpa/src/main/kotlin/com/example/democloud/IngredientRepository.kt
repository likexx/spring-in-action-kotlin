package com.example.democloud

import org.springframework.data.repository.CrudRepository

interface IngredientRepository : CrudRepository<Ingredient, String> {
}