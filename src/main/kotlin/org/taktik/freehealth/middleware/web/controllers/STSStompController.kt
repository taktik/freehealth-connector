package org.taktik.freehealth.middleware.web.controllers

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller
import org.taktik.freehealth.middleware.domain.sts.StompMessage
import org.taktik.freehealth.middleware.service.RemoteKeystoreService
import java.security.Principal
import java.util.*

@Controller
class STSStompController(val remoteKeystoreService: RemoteKeystoreService) {
    @MessageMapping("/sts")
    fun connectSts(principal: Principal) {
        remoteKeystoreService.registerConnection(UUID.fromString(principal.name))
    }

    @MessageMapping("/msg")
    fun connectSts(principal: Principal, message: StompMessage) {
        remoteKeystoreService.publishResponse(UUID.fromString(principal.name), message.content)
    }
}
