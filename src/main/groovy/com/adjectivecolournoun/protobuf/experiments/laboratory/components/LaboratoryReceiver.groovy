package com.adjectivecolournoun.protobuf.experiments.laboratory.components

import com.adjectivecolournoun.protobuf.experiments.Experiments
import groovy.util.logging.Slf4j
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

@Component
@Slf4j
class LaboratoryReceiver {

    @JmsListener(destination = 'laboratory')
    byte[] performExperiment(byte[] hypothesisBytes) {
        log.debug 'Performing experiment...'

        def experiment = Experiments.Experiment.parseFrom(hypothesisBytes)

        def experimentalVerification = Experiments.ExperimentalVerification.newBuilder().setId(experiment.id).build()

        experimentalVerification.toByteArray()
    }
}
