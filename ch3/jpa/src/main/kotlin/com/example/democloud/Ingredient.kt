package com.example.democloud

import java.util.UUID
import javax.persistence.*

@Entity
class Ingredient {
    @Id
    var id = ""

    var name = ""

    @Enumerated(EnumType.STRING)
    var type: Ingredient.Type = Type.WRAP

    enum class Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
