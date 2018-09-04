
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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
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
import javax.servlet.Filter

@Configuration
class SecurityConfig {
	@Bean fun passwordEncoder() = BCryptPasswordEncoder(8)
	@Bean fun authenticationProcessingFilterEntryPoint() = LoginUrlAuthenticationEntryPoint("/", mapOf("/api" to "api/login.html"))
	@Bean fun basicAuthenticationFilter(authenticationManager: AuthenticationManager, authenticationProcessingFilterEntryPoint: LoginUrlAuthenticationEntryPoint) = BasicAuthenticationFilter(authenticationManager)
	@Bean fun usernamePasswordAuthenticationFilter(authenticationManager: AuthenticationManager, authenticationProcessingFilterEntryPoint: LoginUrlAuthenticationEntryPoint) = UsernamePasswordAuthenticationFilter().apply {
		usernameParameter = "username"
		passwordParameter = "password"
		setAuthenticationManager(authenticationManager)
		setAuthenticationSuccessHandler(AuthenticationSuccessHandler().apply { setDefaultTargetUrl("/"); setAlwaysUseDefaultTargetUrl(false) })
		setAuthenticationFailureHandler(AuthenticationFailureHandler().apply { setDefaultFailureUrl("/error"); })
		setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/login"))
		setPostOnly(true)
	}
	@Bean fun remotingExceptionTranslationFilter() = ExceptionTranslationFilter(Http401UnauthorizedEntryPoint())
	@Bean fun exceptionTranslationFilter(authenticationProcessingFilterEntryPoint: LoginUrlAuthenticationEntryPoint) = ExceptionTranslationFilter(authenticationProcessingFilterEntryPoint)
	@Bean fun securityConfigAdapter(
			daoAuthenticationProvider:DaoAuthenticationProvider,
			basicAuthenticationFilter : BasicAuthenticationFilter,
			usernamePasswordAuthenticationFilter : UsernamePasswordAuthenticationFilter,
			exceptionTranslationFilter : ExceptionTranslationFilter,
			remotingExceptionTranslationFilter : ExceptionTranslationFilter) = SecurityConfigAdapter(daoAuthenticationProvider, basicAuthenticationFilter, usernamePasswordAuthenticationFilter, exceptionTranslationFilter, remotingExceptionTranslationFilter)

	@Bean fun daoAuthenticationProvider(userDetailsService : UserDetailsService, passwordEncoder: org.springframework.security.crypto.password.PasswordEncoder) = DaoAuthenticationProvider().apply {
		setPasswordEncoder(passwordEncoder)
		setUserDetailsService(userDetailsService)
	}
    @Bean fun httpClient() = HttpClient().apply { start() }
	@Bean fun userDetailsService(httpClient: HttpClient, couchDbProperties: CouchDbProperties, cacheManager: CacheManager) = CouchdbUserDetailsService(httpClient, couchDbProperties, cacheManager)
}

@Configuration
class SecurityConfigAdapter(private val daoAuthenticationProvider: DaoAuthenticationProvider,
                                          private val basicAuthenticationFilter : Filter,
                                          private val usernamePasswordAuthenticationFilter : Filter,
                                          private val exceptionTranslationFilter : Filter,
                                          private val remotingExceptionTranslationFilter : Filter) : WebSecurityConfigurerAdapter(false) {
	@Autowired
	fun configureGlobal(auth: AuthenticationManagerBuilder?) {
		auth!!.authenticationProvider(daoAuthenticationProvider)
	}

	override fun configure(http: HttpSecurity?) {
		http!!.csrf().disable().addFilterBefore(FilterChainProxy(listOf(
            DefaultSecurityFilterChain(AntPathRequestMatcher("/api/**"), basicAuthenticationFilter, usernamePasswordAuthenticationFilter, exceptionTranslationFilter),
            DefaultSecurityFilterChain(AntPathRequestMatcher("/**"), basicAuthenticationFilter, remotingExceptionTranslationFilter))), FilterSecurityInterceptor::class.java)
				.authorizeRequests()

				.antMatchers("/api/login.html").permitAll()
				.antMatchers("/api/css/**").permitAll()
				.antMatchers("/api/**").permitAll() //.hasRole("USER")

				.antMatchers("/").permitAll()
				.antMatchers("/**").permitAll() //.hasRole("USER")
	}
}
