package com.adjectivecolournoun.protobuf.experiments.webservice

import com.adjectivecolournoun.protobuf.experiments.webservice.ws.LaboratoryEndpoint
import com.adjectivecolournoun.protobuf.experiments.webservice.ws.StaticContextConfigurator
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.socket.server.standard.ServerEndpointExporter

import javax.websocket.server.ServerEndpointConfig

@Configuration
@Import([EmbeddedServletContainerAutoConfiguration, WebSocketAutoConfiguration])
class WebSocketConfig {

    @Bean
    LaboratoryEndpoint laboratoryEndpoint() {
        new LaboratoryEndpoint()
    }

    @Bean
    ServerEndpointExporter endpointExporter() {
        new ServerEndpointExporter()
    }

    @Bean
    ServerEndpointConfig.Configurator configurator() {
        new StaticContextConfigurator()
    }
}
