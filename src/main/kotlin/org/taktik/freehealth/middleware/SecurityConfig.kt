
/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.freehealth.middleware

import org.eclipse.jetty.client.HttpClient
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.FilterChainProxy
import org.springframework.security.web.access.ExceptionTranslationFilter
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.taktik.freehealth.middleware.dao.CouchDbProperties
import org.taktik.freehealth.middleware.dao.CouchdbUserDetailsService
import org.taktik.freehealth.middleware.web.AuthenticationFailureHandler
import org.taktik.freehealth.middleware.web.AuthenticationSuccessHandler
import org.taktik.freehealth.middleware.web.Http401UnauthorizedEntryPoint
import org.taktik.freehealth.middleware.web.LoginUrlAuthenticationEntryPoint

@Configuration
class SecurityConfig {
	@Bean fun securityConfigAdapter(httpClient: HttpClient, couchDbProperties: CouchDbProperties, authenticationProperties: AuthenticationProperties, cacheManager: CacheManager) = SecurityConfigAdapter(httpClient, couchDbProperties, authenticationProperties, cacheManager)
    @Bean fun httpClient() = HttpClient().apply { start() }
}

class SecurityConfigAdapter(val httpClient: HttpClient, val couchDbProperties: CouchDbProperties, val authenticationProperties: AuthenticationProperties, val cacheManager: CacheManager) : WebSecurityConfigurerAdapter(false) {
    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    //@Autowired
    override fun configure(auth: AuthenticationManagerBuilder?) {
        val passwordEncoder = BCryptPasswordEncoder(8)
		auth!!.authenticationProvider(DaoAuthenticationProvider().apply {
            setPasswordEncoder(passwordEncoder)
            setUserDetailsService(CouchdbUserDetailsService(httpClient, couchDbProperties, authenticationProperties, cacheManager, passwordEncoder))
        })
	}

	override fun configure(http: HttpSecurity?) {
        val authenticationManager = authenticationManager()
        val loginUrlAuthenticationEntryPoint = LoginUrlAuthenticationEntryPoint("/", mapOf("/api" to "api/login.html"))

        http!!.csrf().disable().addFilterBefore(FilterChainProxy(listOf(
            DefaultSecurityFilterChain(AntPathRequestMatcher("/**"), BasicAuthenticationFilter(authenticationManager), ExceptionTranslationFilter(Http401UnauthorizedEntryPoint())))), FilterSecurityInterceptor::class.java)
				.authorizeRequests()

				.antMatchers("/api/login.html").permitAll()
				.antMatchers("/api/css/**").permitAll()
				.antMatchers("/api/**").permitAll() //.hasRole("USER")

				.antMatchers("/").permitAll()
				.antMatchers("/**").permitAll() //.hasRole("USER")
	}
}
