package com.example.democloud

import org.springframework.data.cassandra.core.mapping.UserDefinedType

@UserDefinedType("ingredients")
class IngredientUDT(val name: String, val type: Ingredient.Type) {
}