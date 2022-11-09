package com.example.democloud.repository

import com.example.democloud.entity.TacoOrder
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<TacoOrder, Long> {
    fun findByDeliveryZip(deliveryZip: String): List<TacoOrder>
}