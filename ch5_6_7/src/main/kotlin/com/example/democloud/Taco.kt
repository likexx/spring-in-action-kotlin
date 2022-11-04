package com.example.democloud

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.PrePersist
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}