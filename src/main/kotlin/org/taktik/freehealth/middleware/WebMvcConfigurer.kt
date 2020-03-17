package org.taktik.freehealth.middleware

import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@ComponentScan
@EnableConfigurationProperties(ServerProperties::class, ResourceProperties::class)
class WebMvcConfigurer(val resourceProperties: ResourceProperties) : WebMvcConfigurer {
    private val SERVLET_LOCATIONS = arrayOf("/")

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

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        val cachePeriod = this.resourceProperties.cache.period
        val cacheControl = this.resourceProperties.cache
            .cachecontrol.toHttpCacheControl()

        registry.addResourceHandler("/**")
            .addResourceLocations(*getResourceLocations(this.resourceProperties.staticLocations))
            .setCachePeriod(cachePeriod?.seconds?.toInt() ?: 3600)
            .setCacheControl(cacheControl)
    }

    internal fun getResourceLocations(staticLocations: Array<String>): Array<String> {
        val locations = arrayOfNulls<String>(staticLocations.size + SERVLET_LOCATIONS.size)
        System.arraycopy(staticLocations, 0, locations, 0, staticLocations.size)
        System.arraycopy(SERVLET_LOCATIONS, 0, locations, staticLocations.size,
                         SERVLET_LOCATIONS.size)
        return locations.filterNotNull().toTypedArray()
    }

}
