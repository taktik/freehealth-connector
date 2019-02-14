package org.taktik.freehealth.middleware.dao

import com.google.gson.Gson
import org.eclipse.jetty.client.HttpClient
import org.springframework.cache.CacheManager
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.net.HttpURLConnection
import java.net.URL
import java.util.Base64
import java.util.UUID

class CouchdbUserDetailsService(val httpClient:HttpClient, val couchDbProperties: CouchDbProperties, val cacheManager : CacheManager, val gson: Gson = Gson()) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? = cacheManager.getCache("USERS")?.get(username) {

        /*val uri = URI.create(couchDbProperties.url + "/" + couchDbProperties.dbName + "/" + UUID.fromString(username).toString())
        val request = httpClient.newRequest(uri)
        request.header(HttpHeader.AUTHORIZATION, "Basic ${Base64.getEncoder().encodeToString("${couchDbProperties.username}:${couchDbProperties.password}".toByteArray())}");

        gson.fromJson(request.send().contentAsString, User::class.java)?.apply {
            authorities.add(SimpleGrantedAuthority("ROLE_USER"))
        }*/

        try {
            val connection = URL(couchDbProperties.url + "/" + couchDbProperties.dbName + "/" + UUID.fromString(username).toString()).openConnection()
            (connection as? HttpURLConnection)?.let {
                val content = it.apply {
                    requestMethod = "GET"
                    setRequestProperty("Authorization", "Basic ${Base64.getEncoder().encodeToString("${couchDbProperties.username}:${couchDbProperties.password}".toByteArray())}")
                }.inputStream.bufferedReader().readText()
                gson.fromJson(content, User::class.java)?.apply {
                    authorities.add(SimpleGrantedAuthority("ROLE_USER"))
                }
            }
        } catch(e:Exception) {
            throw e
        }
    }
}
