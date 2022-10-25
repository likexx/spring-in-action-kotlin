package com.example.democloud

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class JdbcIngredientRepository (@Autowired val jdbcTemplate: JdbcTemplate) : IngredientRepository {
    val rowMapper = RowMapper<Ingredient> { rs, _ ->
        Ingredient(id = rs.getString("id"),
        name = rs.getString("name"),
        type = Ingredient.Type.valueOf(rs.getString("type")))
    }

    override fun findAll(): Iterable<Ingredient> {
        return jdbcTemplate.query("select id, name, type from ingredient", rowMapper)
    }

    override fun findById(id: String): Ingredient? {
        val res = jdbcTemplate.query("select id, name, type from ingredient where id=?", rowMapper, id)
        return if (res.size==0) null else res[0]
    }

    override fun save(ingredient: Ingredient): Ingredient {
        jdbcTemplate.update(
            "insert into ingredient (id, name, type) values (?, ?, ?)",
            ingredient.id.toString(),
            ingredient.name,
            ingredient.type.toString())
        return ingredient
    }

}