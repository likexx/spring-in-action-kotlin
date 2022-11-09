package com.example.democloud.api

import com.example.democloud.entity.TacoOrder
import com.example.democloud.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path=["/api/order"],
                produces = ["application/json"])
@CrossOrigin(origins = ["*"])
class OrderAPI(@Autowired val orderRepo: OrderRepository) {
    @GetMapping("/recent")
    fun recentOrders(): Iterable<TacoOrder> {
        val pageRequest = PageRequest.of(0, 20, Sort.by("createdAt").descending())
        return orderRepo.findAll(pageRequest).content
    }

    @GetMapping("/id/{orderId}")
    fun getDetails(@PathVariable("orderId") orderId: Long): TacoOrder {
        return orderRepo.findById(orderId).get()
    }

    @GetMapping("/zipcode/{zipcode}")
    fun getDetails(@PathVariable("zipcode") zipcode: String): Iterable<TacoOrder> {
        return orderRepo.findByDeliveryZip(zipcode)
    }

}