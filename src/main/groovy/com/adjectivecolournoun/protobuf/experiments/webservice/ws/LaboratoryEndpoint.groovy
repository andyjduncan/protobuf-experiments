package com.adjectivecolournoun.protobuf.experiments.webservice.ws

import com.adjectivecolournoun.protobuf.experiments.Experiments
import com.adjectivecolournoun.protobuf.experiments.Experiments.ExperimentalVerification
import com.adjectivecolournoun.protobuf.experiments.Experiments.Hypothesis
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator

import javax.jms.BytesMessage
import javax.jms.Session
import javax.jms.TextMessage
import javax.websocket.OnMessage
import javax.websocket.server.ServerEndpoint

@ServerEndpoint(
        value = '/lab',
        configurator = StaticContextConfigurator,
        decoders = HypothesisDecoder,
        encoders = ExperimentalVerificationEncoder
)
@Slf4j
class LaboratoryEndpoint {

    @Autowired
    JmsTemplate jmsTemplate

    @OnMessage
    ExperimentalVerification handleMessage(Hypothesis hypothesis) {
        log.debug 'Testing hypothesis...'
        def experiment = Experiments.Experiment.newBuilder().setId(hypothesis.id).build()
        BytesMessage reply = jmsTemplate.sendAndReceive('laboratory', { Session session ->
            def message = session.createBytesMessage()
            message.writeBytes experiment.toByteArray()
            message
        } as MessageCreator)

        def bytes = new byte[reply.bodyLength]
        reply.readBytes(bytes)

        ExperimentalVerification.parseFrom(bytes)
    }
}
