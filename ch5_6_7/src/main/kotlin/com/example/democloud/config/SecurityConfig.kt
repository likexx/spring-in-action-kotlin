package com.example.democloud.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix="security.urls")
class SecurityConfig(@Autowired val userDetailsSerivce: UserDetailsService) {
    lateinit var whitelist: MutableList<String>
    lateinit var authrequired: MutableList<String>

    companion object {
        val log: Logger? = LoggerFactory.getLogger(this::class.java)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun designFilterChain(http: HttpSecurity): SecurityFilterChain {
        log?.debug("====>authrequired: $authrequired")
        log?.debug("====>whitelist: $whitelist")

         http.authorizeRequests()
                .antMatchers(*authrequired.toTypedArray()).hasRole("USER")
                .antMatchers(*whitelist.toTypedArray()).permitAll()
                .and()
                .formLogin()
                .permitAll()
                 .and()
                 .logout()
                 .logoutSuccessUrl("/")
        return http.build()
    }
}
