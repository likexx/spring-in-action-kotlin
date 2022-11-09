package com.example.democloud.controller

import com.example.democloud.entity.Ingredient
import com.example.democloud.entity.Taco
import com.example.democloud.entity.TacoOrder
import com.example.democloud.repository.IngredientRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController (@Autowired val ingredientRepository: IngredientRepository) {
    companion object {
        val log: Logger? = LoggerFactory.getLogger(this::class.java)
    }

    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients:MutableCollection<Ingredient> = ingredientRepository.findAll() as MutableCollection<Ingredient>

        val groups = ingredients.associateBy { it.type }

        Ingredient.Type.values().forEach {
            val types = groups[it]?: listOf<Ingredient>()
            model[it.toString().lowercase()] = types
        }
    }

    @ModelAttribute(name="tacoOrder")
    fun order(): TacoOrder {
        return TacoOrder()
    }

    @ModelAttribute(name="taco")
    fun taco(): Taco {
        return Taco()
    }

    @GetMapping
    fun showDesignForm(): String {
        return "design"
    }

    @PostMapping
    fun processTaco(@Valid taco: Taco,
                    errors: Errors,
                    @ModelAttribute tacoOrder: TacoOrder
    ): String {
        log?.info("name: ${taco.name}, ingredients: ${taco.ingredients.size}")
        if (errors.hasErrors()) {
            for (err in errors.allErrors) {
                log?.error(err.defaultMessage)
            }
            return "design";
        }

        tacoOrder.addTaco(taco)
        log?.info("Process taco: ${taco.toString()}")
        return "redirect:/orders/current"
    }
}