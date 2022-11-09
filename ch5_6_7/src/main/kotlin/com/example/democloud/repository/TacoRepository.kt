package com.example.democloud.repository

import com.example.democloud.entity.Taco
import org.springframework.data.repository.CrudRepository

interface TacoRepository : CrudRepository<Taco, Long> {

}