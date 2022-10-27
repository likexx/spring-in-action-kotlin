package com.example.democloud

import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.boot.test.mock.mockito.MockBean

@WebMvcTest
class HomeControllerTests (
    @Autowired val mockMvc: MockMvc,
    @MockBean @Autowired val ingredientRepository: IngredientRepository
    ) {
    @Test
    fun testHomepage() {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk)
            .andExpect(view().name("home"))
            .andExpect(content().string(Matchers.containsString("Welcome to...")))
    }
}