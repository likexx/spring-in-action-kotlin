package com.example.democloud.entity

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class Taco {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    var id: String = ""

    @NotNull
    @Size(min=5, message="at least 5 characters")
    var name = "";

    var createdAt: Date = Date()

    @NotNull
    @Size(min=1, message="at least 1 ingredient")
    @ManyToMany
    var ingredients = mutableListOf<Ingredient>();

    fun addIngredient(ingredient: Ingredient) {
        ingredients.add(ingredient)
    }

    override fun toString(): String {
        return "id=$id, name=$name, createAt=$createdAt"
    }
}