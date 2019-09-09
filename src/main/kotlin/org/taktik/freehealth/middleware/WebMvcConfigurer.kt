package org.taktik.freehealth.middleware

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.taktik.freehealth.middleware.web.ExceptionHandlers

@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@ComponentScan
class WebMvcConfigurer : WebMvcConfigurer {
    override fun configureHandlerExceptionResolvers(exceptionResolvers: MutableList<HandlerExceptionResolver>) {
        exceptionResolvers.add(ExceptionHandlers());
    }

    override fun configureContentNegotiation(contentNegotiationConfigurer: ContentNegotiationConfigurer) {
        // Disable use of pathExtension and parameter for content negotiation
        contentNegotiationConfigurer.favorPathExtension(false)
        contentNegotiationConfigurer.favorParameter(false)
    }

    override fun configurePathMatch(pathMatchConfigurer: PathMatchConfigurer) {
        // Disable suffix pattern and trailing slash matching
        pathMatchConfigurer.isUseSuffixPatternMatch = false
        pathMatchConfigurer.isUseTrailingSlashMatch = false
    }
}
