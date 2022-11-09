package com.example.democloud

import com.example.democloud.entity.Ingredient
import com.example.democloud.repository.IngredientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.util.*

@Component
class IngredientByIdConverter(@Autowired val ingredientRepository: IngredientRepository) : Converter<String, Ingredient> {

    override fun convert(id: String): Ingredient {
        return ingredientRepository.findById(id).orElse(null) ?: Ingredient()
    }
}