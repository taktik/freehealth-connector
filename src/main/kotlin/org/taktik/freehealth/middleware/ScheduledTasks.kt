package org.taktik.freehealth.middleware

import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointDistributor
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointUpdater
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduledTasks {
    @Scheduled(fixedDelay = 60000)
    fun scheduleFixedDelayTask() {
        if (EndpointDistributor.getInstance().isBCPMode) {
            EndpointUpdater.forceUpdate()
        }
    }
}
