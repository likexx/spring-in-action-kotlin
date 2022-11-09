package com.example.democloud.repository

import com.example.democloud.entity.Ingredient
import org.springframework.data.repository.CrudRepository

interface IngredientRepository : CrudRepository<Ingredient, String> {
}