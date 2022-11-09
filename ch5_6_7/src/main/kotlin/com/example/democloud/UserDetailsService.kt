package com.example.democloud

import com.example.democloud.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class DemoUserDetailsService(@Autowired val repo: UserRepository): UserDetailsService {
    companion object {
        val log: Logger? = LoggerFactory.getLogger(this::class.java)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        log?.debug("loadUserByUsername: $username")
        return repo.findByUsername(username!!)
    }
}