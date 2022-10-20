package com.example.democloud

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

class Taco {
    @NotNull
    @Size(min=5, message="at least 5 characters")
    var name = "";

    @NotNull
    @Size(min=1, message="at least 1 ingredient")
    var ingredients = mutableListOf<Ingredient>();

}