package com.example.democloud

import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import org.springframework.data.cassandra.core.mapping.UserDefinedType
import java.util.*
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table("tacos")
class Taco {
    @PrimaryKeyColumn(type=PrimaryKeyType.PARTITIONED)
    var id: String = ""

    @NotNull
    @Size(min=5, message="at least 5 characters")
    var name = "";

    @PrimaryKeyColumn(type=PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    var createdAt: Date = Date()

    @NotNull
    @Size(min=1, message="at least 1 ingredient")
    @Column("ingredients")
    var ingredients = mutableListOf<IngredientUDT>();

    fun addIngredient(ingredient: Ingredient) {
        ingredients.add(IngredientUDT(name=ingredient.name, type=ingredient.type))
    }
}

@UserDefinedType("taco")
class TacoUDT(val name: String, val ingredients: List<IngredientUDT>) {

}