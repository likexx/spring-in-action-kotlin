package com.example.democloud

import org.hibernate.validator.constraints.CreditCardNumber
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Table("orders")
class TacoOrder {
    @PrimaryKey
    var id: UUID = UUID.randomUUID()

    @NotBlank
    var deliveryName = ""

    @NotBlank
    var deliveryStreet = ""

    @NotBlank
    var deliveryCity = ""

    @NotBlank
    var deliveryState = ""

    @NotBlank
    var deliveryZip = ""

    @CreditCardNumber
    var ccNumber = ""

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
        message="Must be formatted MM/YY")
    var ccExpiration = ""

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    var ccCVV = ""

    var createdAt = Date()

    @Column("tacos")
    var tacos = mutableListOf<TacoUDT>()

    fun addTaco(taco:Taco) {
        tacos.add(TacoUDT(name=taco.name, taco.ingredients.map { IngredientUDT(name=it.name, type=it.type) }))
    }
}