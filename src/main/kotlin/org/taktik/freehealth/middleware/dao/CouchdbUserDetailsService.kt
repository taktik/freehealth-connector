package org.taktik.freehealth.middleware.dao

import com.google.gson.Gson
import org.apache.commons.codec.digest.DigestUtils
import org.eclipse.jetty.client.HttpClient
import org.eclipse.jetty.client.util.BasicAuthentication
import org.eclipse.jetty.http.HttpHeader
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.net.URI
import java.util.Base64
import java.util.UUID

class CouchdbUserDetailsService(val httpClient:HttpClient, val couchDbProperties: CouchDbProperties, val cacheManager : CacheManager, val gson: Gson = Gson()) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? = cacheManager.getCache("USERS")?.get(username) {

        val uri = URI.create(couchDbProperties.url + "/" + couchDbProperties.dbName + "/" + UUID.fromString(username).toString())
        val request = httpClient.newRequest(uri)
        request.header(HttpHeader.AUTHORIZATION, "Basic ${Base64.getEncoder().encodeToString("${couchDbProperties.username}:${couchDbProperties.password}".toByteArray())}");

        gson.fromJson(request.send().contentAsString, User::class.java)?.apply {
            authorities.add(SimpleGrantedAuthority("ROLE_USER"))
        }
    }
}
