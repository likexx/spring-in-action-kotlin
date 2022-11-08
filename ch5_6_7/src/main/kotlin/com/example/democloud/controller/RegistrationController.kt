package com.example.democloud.controller

import com.example.democloud.User
import com.example.democloud.UserRepository
import com.example.democloud.config.SecurityConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/register")
class RegistrationController(
    @Autowired val userRepo: UserRepository,
    @Autowired val passwordEncoder: PasswordEncoder) {

    @GetMapping
    fun registerForm(): String {
        return "registration"
    }

    @PostMapping
    fun processRegistration(form: RegistrationForm): String {
        userRepo.save(form.toUser(passwordEncoder))
        return "redirect:/login"
    }
}

class RegistrationForm(
    val username: String,
    val password: String) {

    fun toUser(passwordEncoder: PasswordEncoder): User {
        return User(username=username, password=passwordEncoder.encode(password))
    }

}