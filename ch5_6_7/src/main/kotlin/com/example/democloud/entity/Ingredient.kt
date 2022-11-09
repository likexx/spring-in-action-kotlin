package com.example.democloud.entity

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
class Ingredient() {
    @Id
    var id = ""

    var name = ""

    @Enumerated(EnumType.STRING)
    var type: Ingredient.Type = Type.WRAP

    constructor(id: String, name: String, type: Ingredient.Type) : this() {
        this.id = id
        this.name = name
        this.type = type
    }

    enum class Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
