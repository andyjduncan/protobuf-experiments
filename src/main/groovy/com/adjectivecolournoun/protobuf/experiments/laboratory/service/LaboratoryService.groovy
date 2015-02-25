package com.adjectivecolournoun.protobuf.experiments.laboratory.service

import com.adjectivecolournoun.protobuf.experiments.Experiments.ExperimentalVerification
import com.adjectivecolournoun.protobuf.experiments.Experiments.Experiment
import org.springframework.stereotype.Service

@Service
class LaboratoryService {

    ExperimentalVerification performExperiment(Experiment experiment) {
        ExperimentalVerification.newBuilder().setId(experiment.id).build()
    }
}
