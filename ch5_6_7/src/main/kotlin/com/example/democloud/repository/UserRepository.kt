package com.example.democloud.repository

import com.example.democloud.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>{
    fun findByUsername(username: String): User
}