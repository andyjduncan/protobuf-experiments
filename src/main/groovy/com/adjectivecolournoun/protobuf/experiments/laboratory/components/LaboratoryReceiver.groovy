package com.adjectivecolournoun.protobuf.experiments.laboratory.components

import com.adjectivecolournoun.protobuf.experiments.Experiments
import com.adjectivecolournoun.protobuf.experiments.laboratory.service.LaboratoryService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

@Component
@Slf4j
class LaboratoryReceiver {

    @Autowired
    LaboratoryService laboratoryService

    @JmsListener(destination = 'laboratory')
    byte[] performExperiment(byte[] experimentBytes) {
        log.debug 'Performing experiment...'

        def experiment = Experiments.Experiment.parseFrom(experimentBytes)

        def experimentalVerification = laboratoryService.performExperiment(experiment)

        experimentalVerification.toByteArray()
    }
}
