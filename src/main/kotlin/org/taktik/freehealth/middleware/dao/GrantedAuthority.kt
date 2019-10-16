package org.taktik.freehealth.middleware.dao

class GrantedAuthority(var role: String = "DEFAULT") : org.springframework.security.core.GrantedAuthority {
    override fun getAuthority() = role

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GrantedAuthority) return false

        if (role != other.role) return false

        return true
    }

    override fun hashCode(): Int {
        return role.hashCode()
    }

}
