package com.example.democloud

import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    companion object {
        val log: Logger? = LoggerFactory.getLogger(this::class.java)
    }

    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients = listOf(
            Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
            Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
            Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
            Ingredient("CARN", "Carnitas", Ingredient.Type.VEGGIES),
            Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
            Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
            Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
            Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
            Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
            Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        )

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
                    @ModelAttribute tacoOrder: TacoOrder): String {
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