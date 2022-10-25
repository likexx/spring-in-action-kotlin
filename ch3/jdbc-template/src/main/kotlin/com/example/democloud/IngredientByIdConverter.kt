package com.example.democloud

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class IngredientByIdConverter(@Autowired val ingredientRepository: IngredientRepository) : Converter<String, Ingredient> {

    override fun convert(id: String): Ingredient {
        return ingredientRepository.findById(id)!!
    }
}