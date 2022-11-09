package com.example.democloud.repository

import com.example.democloud.entity.TacoOrder
import org.springframework.data.repository.PagingAndSortingRepository

interface OrderRepository : PagingAndSortingRepository<TacoOrder, Long> {
    fun findByDeliveryZip(deliveryZip: String): List<TacoOrder>
}