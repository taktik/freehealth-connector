package org.taktik.freehealth.middleware.dao

import org.springframework.security.core.userdetails.UserDetails

class User(var _id:String? = null, var passwordHash: String? = null, var fullName:String? = null, var mcnLicense:String? = null, var mcnPassword: String? = null, var authorities: MutableSet<GrantedAuthority> = HashSet()) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities

    override fun isEnabled() = true
    override fun getUsername() = _id!!
    override fun isCredentialsNonExpired() = true
    override fun getPassword() = passwordHash!!
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
}
