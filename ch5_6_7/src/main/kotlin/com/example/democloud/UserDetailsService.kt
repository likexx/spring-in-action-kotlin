package com.example.democloud

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

//@Bean
//fun userAuthService(@Autowired encoder: PasswordEncoder): UserDetailsService? {
//    val usersList: MutableList<UserDetails> = ArrayList()
//    usersList.add(
//        User(
//            "buzz", encoder.encode("password"),
//            Arrays.asList(SimpleGrantedAuthority("ROLE_USER"))
//        )
//    )
//    usersList.add(
//        User(
//            "woody", encoder.encode("password"),
//            Arrays.asList(SimpleGrantedAuthority("ROLE_USER"))
//        )
//    )
//    return InMemoryUserDetailsManager(usersList)
//}

@Service
class DemoUserDetailsService: UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val encoder = BCryptPasswordEncoder()
        return User(
            "buzz", encoder.encode("password"),
            Arrays.asList(SimpleGrantedAuthority("ROLE_USER"))
        )
    }
}

//@Service
//open class DemoUserDetailsService(@Autowired val repo: UserRepository) : UserDetailsService {
//    companion object {
//        val log: Logger? = LoggerFactory.getLogger(this::class.java)
//    }
//
//    override fun loadUserByUsername(username: String): UserDetails {
//        log?.debug("======> loadUserByUsername $username")
////        return repo.findByUsername(username)
//        return User("guest", "12345")
//    }
//}