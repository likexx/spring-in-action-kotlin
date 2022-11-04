package com.example.democloud

import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<TacoOrder, Long> {
    fun findByDeliveryZip(deliveryZip: String): List<TacoOrder>
}