package com.example.democloud

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("ingredients")
class Ingredient {
    @PrimaryKey
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
