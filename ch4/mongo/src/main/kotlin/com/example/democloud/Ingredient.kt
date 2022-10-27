package com.example.democloud

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection="ingredients")
class Ingredient {
    @Id
    var id = ""

    var name = ""

    var type: Ingredient.Type = Type.WRAP

    enum class Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    constructor(id:String, name:String, type:Ingredient.Type) {
        this.id=id
        this.name=name
        this.type=type
    }

}
