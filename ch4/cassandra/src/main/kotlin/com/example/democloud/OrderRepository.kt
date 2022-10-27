package com.example.democloud

import org.springframework.data.repository.CrudRepository
import java.util.*

interface OrderRepository : CrudRepository<TacoOrder, UUID>