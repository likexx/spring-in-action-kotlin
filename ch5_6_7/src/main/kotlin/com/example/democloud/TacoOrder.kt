package com.example.democloud

import org.hibernate.validator.constraints.CreditCardNumber
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Entity
class TacoOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

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

    @OneToMany
    var tacos = mutableListOf<Taco>()

    fun addTaco(taco:Taco) {
        tacos.add(taco)
    }
}