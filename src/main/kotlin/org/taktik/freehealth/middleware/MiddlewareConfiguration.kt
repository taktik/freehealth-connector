package org.taktik.freehealth.middleware

import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointDistributor
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointUpdater
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler


@Configuration
class MiddlewareConfiguration {
    @Bean
    fun threadPoolTaskScheduler() = ThreadPoolTaskScheduler().apply {
        poolSize = 5
        threadNamePrefix = "ThreadPoolTaskScheduler"
    }
}
