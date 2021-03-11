package org.taktik.freehealth.middleware.dao

import com.google.gson.Gson
import org.eclipse.jetty.client.HttpClient
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.taktik.freehealth.middleware.AuthenticationProperties
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.Base64
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class CouchdbUserDetailsService(val httpClient: HttpClient,
    val couchDbProperties: CouchDbProperties,
    val authenticationProperties: AuthenticationProperties,
    val passwordEncoder: PasswordEncoder,
    val gson: Gson = Gson()
    ) : UserDetailsService {
    private val TTL = couchDbProperties.cachettl.toLong()
    private val hashedPassword = authenticationProperties.password?.let {passwordEncoder.encode(it)}
    private val cache = ConcurrentHashMap<String, Pair<Long,UserDetails>>()

    override fun loadUserByUsername(username: String): UserDetails = cache.computeIfAbsent(username) {
        loadUserInternal(username).let { System.currentTimeMillis() + TTL to it }
    }.let { (time, user) ->
        if (time < System.currentTimeMillis()) {
            try {
                loadUserInternal(username).also { cache[username] = System.currentTimeMillis() + TTL to it }
            } catch(e: IOException) {
                cache[username] = System.currentTimeMillis() + TTL * 10 to user //Back off
                user
            } catch(e: Exception) {
                user
            }
        } else user
    }

    private fun loadUserInternal(username: String) = (if (username == authenticationProperties.username)
        User(
            _id = authenticationProperties.username,
            passwordHash = hashedPassword ?: "",
            mcnLicense = authenticationProperties.mcnLicense,
            mcnPassword = authenticationProperties.mcnPassword
        )
    else try {
        val connection = URL(
            couchDbProperties.url + "/" + couchDbProperties.dbName + "/" + UUID.fromString(username).toString()
        ).openConnection()
        (connection as? HttpURLConnection)?.let {
            val content = it.apply {
                requestMethod = "GET"
                setRequestProperty(
                    "Authorization",
                    "Basic ${
                        Base64.getEncoder()
                            .encodeToString("${couchDbProperties.username}:${couchDbProperties.password}".toByteArray())
                    }"
                )
            }.inputStream.bufferedReader().readText()
            gson.fromJson(content, User::class.java)?.apply {
                authorities.add(GrantedAuthority("ROLE_USER"))
            }
        } ?: throw IllegalStateException("Cannot load User from database")
    } catch (e: Exception) {
        throw e
    })
}
