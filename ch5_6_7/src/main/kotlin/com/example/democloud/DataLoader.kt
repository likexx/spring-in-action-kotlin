package com.example.democloud

import com.example.democloud.entity.Ingredient
import com.example.democloud.repository.IngredientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(@Autowired val ingredientRepository: IngredientRepository) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        ingredientRepository.save(Ingredient(id="FLTO", name="Flour Tortilla", Ingredient.Type.WRAP))
        ingredientRepository.save(Ingredient(id="COTO", name="Corn Tortilla", Ingredient.Type.WRAP))
        ingredientRepository.save(Ingredient(id="GRBF", name="Ground Beef", Ingredient.Type.PROTEIN))
        ingredientRepository.save(Ingredient(id="CARN", name="Carnitas", Ingredient.Type.PROTEIN))
        ingredientRepository.save(Ingredient(id="TMTO", name="Diced Tomatoes", Ingredient.Type.VEGGIES))
        ingredientRepository.save(Ingredient(id="LETC", name="Lettuce", Ingredient.Type.VEGGIES))
        ingredientRepository.save(Ingredient(id="CHED", name="Cheddar", Ingredient.Type.CHEESE))
        ingredientRepository.save(Ingredient(id="JACK", name="Monterrey Jack", Ingredient.Type.CHEESE))
        ingredientRepository.save(Ingredient(id="SLSA", name="Salsa", Ingredient.Type.SAUCE))
        ingredientRepository.save(Ingredient(id="SRCR", name="Sour Cream", Ingredient.Type.SAUCE))
    }
}