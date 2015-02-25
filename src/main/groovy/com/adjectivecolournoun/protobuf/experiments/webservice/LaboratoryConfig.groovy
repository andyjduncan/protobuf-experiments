package com.adjectivecolournoun.protobuf.experiments.webservice

import com.adjectivecolournoun.protobuf.experiments.webservice.ws.LaboratoryEndpoint
import com.adjectivecolournoun.protobuf.experiments.webservice.ws.StaticContextConfigurator
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.socket.server.standard.ServerEndpointExporter

import javax.websocket.server.ServerEndpointConfig

@Configuration
@ComponentScan(basePackages = 'com.adjectivecolournoun.protobuf.experiments.webservice.ws')
@Import([EmbeddedServletContainerAutoConfiguration, WebSocketAutoConfiguration])
class LaboratoryConfig {

    @Bean
    LaboratoryEndpoint helloEndpoint() {
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
