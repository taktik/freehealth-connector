package org.taktik.freehealth.middleware.dao

import com.google.gson.Gson
import org.eclipse.jetty.client.HttpClient
import org.springframework.cache.CacheManager
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.taktik.freehealth.middleware.AuthenticationProperties
import java.net.HttpURLConnection
import java.net.URL
import java.util.Base64
import java.util.UUID

class CouchdbUserDetailsService(val httpClient: HttpClient,
    val couchDbProperties: CouchDbProperties,
    val authenticationProperties: AuthenticationProperties,
    val cacheManager: CacheManager,
    val passwordEncoder: PasswordEncoder,
    val gson: Gson = Gson()
    ) : UserDetailsService {
    private val hashedPassword = authenticationProperties.password?.let {passwordEncoder.encode(it)}

    override fun loadUserByUsername(username: String): UserDetails? = cacheManager.getCache("USERS")?.get(username) {
        if (username == authenticationProperties.username)
            User(
                _id=authenticationProperties.username,
                passwordHash = hashedPassword ?: "",
                mcnLicense = authenticationProperties.mcnLicense,
                mcnPassword = authenticationProperties.mcnPassword
                )
        else try {
            val connection = URL(couchDbProperties.url + "/" + couchDbProperties.dbName + "/" + UUID.fromString(username).toString()).openConnection()
            (connection as? HttpURLConnection)?.let {
                val content = it.apply {
                    requestMethod = "GET"
                    setRequestProperty("Authorization", "Basic ${Base64.getEncoder().encodeToString("${couchDbProperties.username}:${couchDbProperties.password}".toByteArray())}")
                }.inputStream.bufferedReader().readText()
                gson.fromJson(content, User::class.java)?.apply {
                    authorities.add(GrantedAuthority("ROLE_USER"))
                }
            }
        } catch(e:Exception) {
            throw e
        }
    }
}
