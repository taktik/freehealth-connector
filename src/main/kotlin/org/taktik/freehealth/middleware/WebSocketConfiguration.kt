package org.taktik.freehealth.middleware

import org.springframework.context.annotation.Configuration
import org.springframework.http.server.ServerHttpRequest
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import org.taktik.freehealth.middleware.domain.sts.StompPrincipal
import java.util.*


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfiguration : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/topic")
        config.setApplicationDestinationPrefixes("/app")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws").setHandshakeHandler(object:DefaultHandshakeHandler() {
            override fun determineUser(
                request: ServerHttpRequest,
                wsHandler: WebSocketHandler,
                attributes: MutableMap<String, Any>
            ) = StompPrincipal(UUID.randomUUID().toString())
        })
    }
}
