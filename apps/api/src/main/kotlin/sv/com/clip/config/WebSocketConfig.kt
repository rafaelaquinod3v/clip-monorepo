package sv.com.clip.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
  override fun configureMessageBroker(registry: MessageBrokerRegistry) {
    registry.enableSimpleBroker("/import-omw")
    registry.setApplicationDestinationPrefixes("/web")
  }
  override fun registerStompEndpoints(registry: StompEndpointRegistry) {
    registry.addEndpoint("/ws-progress").setAllowedOrigins("http://localhost:4200").withSockJS()
  }

  override fun configureWebSocketTransport(registration: WebSocketTransportRegistration) {
    registration.setMessageSizeLimit(1024 * 1024) // 1MB (aumentar si es necesario)
    registration.setSendBufferSizeLimit(1024 * 1024) // 1MB
    registration.setSendTimeLimit(20000) // 20 segundos
  }
}
