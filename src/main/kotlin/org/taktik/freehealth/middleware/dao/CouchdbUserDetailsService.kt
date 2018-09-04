package org.taktik.freehealth.middleware.dao

import com.google.gson.Gson
import org.eclipse.jetty.client.HttpClient
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.UUID

class CouchdbUserDetailsService(val httpClient:HttpClient, val couchDbProperties: CouchDbProperties, val cacheManager : CacheManager, val gson: Gson = Gson()) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails = cacheManager.getCache("USERS").get(username) {
        val user = gson.fromJson(httpClient.GET(couchDbProperties.url + "/" + couchDbProperties.dbName + "/" + UUID.fromString(username).toString()).contentAsString, User::class.java)
        user.authorities.add(SimpleGrantedAuthority("ROLE_USER"))
         user
    }
}
