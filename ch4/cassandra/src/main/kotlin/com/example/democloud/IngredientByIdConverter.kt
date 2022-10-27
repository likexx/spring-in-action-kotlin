package com.example.democloud

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.util.*

@Component
class IngredientByIdConverter(@Autowired val ingredientRepository: IngredientRepository) : Converter<String, Ingredient> {
    override fun convert(id: String): Ingredient {
        return ingredientRepository.findById(id).orElse(null) ?: Ingredient(id="",name="", type=Ingredient.Type.WRAP)
    }
}

@Component
class IngredientUDTByIdConverter(@Autowired val ingredientRepository: IngredientRepository) : Converter<String, IngredientUDT> {
    override fun convert(id: String): IngredientUDT {
        val ingredient = ingredientRepository.findById(id).orElse(null) ?: Ingredient(id="",name="", type=Ingredient.Type.WRAP)
        return IngredientUDT(name=ingredient.name, type=ingredient.type)
    }
}