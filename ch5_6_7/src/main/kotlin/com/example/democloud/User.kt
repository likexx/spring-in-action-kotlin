//package com.example.democloud
//
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
//import javax.persistence.Entity
//import javax.persistence.GeneratedValue
//import javax.persistence.GenerationType
//import javax.persistence.Id
//
//@Entity
//class User : UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    var id: Long = 0
//
//    private var username: String = ""
//    private var password: String = ""
//
//    constructor(username: String, password: String) {
//        this.username = username
//        this.password = password
//    }
//
//    override fun getUsername(): String = username
//
//    override fun getPassword(): String = password
//
//    override fun getAuthorities(): Set<out GrantedAuthority>? = setOf(SimpleGrantedAuthority("ROLE_USER"))
//
//    override fun isAccountNonExpired(): Boolean = true
//
//    override fun isAccountNonLocked(): Boolean = true
//
//    override fun isCredentialsNonExpired(): Boolean = true
//
//    override fun isEnabled(): Boolean = true
//}