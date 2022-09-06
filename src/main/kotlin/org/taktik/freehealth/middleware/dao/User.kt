package org.taktik.freehealth.middleware.dao

import org.springframework.security.core.userdetails.UserDetails

class User(
    var _id: String? = null,
    var passwordHash: String? = null,
    var fullName: String? = null,
    var mcnPackageName: String? = null,
    var mcnLicense: String? = null,
    var mcnPassword: String? = null,
    var apbCustomerId: String? = null,
    var apbPassword: String? = null,
    var ftmCustomerId: String? = null,
    var ftmPassword: String? = null,
    var orgKeystoreAccUuid: String? = null,
    var orgKeystoreAccPassword: String? = null,
    var orgKeystoreProdUuid: String? = null,
    var orgKeystoreProdPassword: String? = null,
    var authorities: MutableSet<GrantedAuthority> = HashSet()
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities

    override fun isEnabled() = true
    override fun getUsername() = _id!!
    override fun isCredentialsNonExpired() = true
    override fun getPassword() = passwordHash!!
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
}
