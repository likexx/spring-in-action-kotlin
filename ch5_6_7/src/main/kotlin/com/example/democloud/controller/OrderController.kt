package com.example.democloud.controller

import com.example.democloud.entity.Taco
import com.example.democloud.entity.TacoOrder
import com.example.democloud.repository.OrderRepository
import com.example.democloud.repository.TacoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import javax.validation.Valid

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
class OrderController(
    @Autowired val orderRepo: OrderRepository,
    @Autowired val tacoRepo: TacoRepository
    ) {
    companion object {
        val log = LoggerFactory.getLogger(this::class.java)
    }

    @GetMapping("/current")
    fun orderForm(): String {
        return "orderForm"
    }

    @PostMapping
    fun processOrder(@Valid order: TacoOrder,
                     errors: Errors,
                     @ModelAttribute tacoOrder: TacoOrder,
                     sessionStatus: SessionStatus): String {
        if (errors.hasErrors()) {
            for (err in errors.allErrors) {
                log?.error(err.defaultMessage)
            }
            return "orderForm"
        }

        val savedTacos = mutableListOf<Taco>()
        tacoOrder.tacos.forEach {
            log.info("processing taco: ${it.toString()}")
            val saved = tacoRepo.save(it)
            log.info("saved taco: ${saved.toString()}")
            savedTacos.add(saved)
        }

        order.tacos.removeAll(order.tacos)
        savedTacos.forEach {
            log.info("add new taco: ${it.toString()}")
            order.tacos.add(it)
        }

        orderRepo.save(order)

        log.info("order submitted: ${order.toString()}")
        sessionStatus.setComplete()

        return "redirect:/"
    }
}