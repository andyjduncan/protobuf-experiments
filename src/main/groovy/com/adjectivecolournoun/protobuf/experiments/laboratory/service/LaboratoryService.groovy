package com.adjectivecolournoun.protobuf.experiments.laboratory.service

import com.adjectivecolournoun.protobuf.experiments.Experiments.ExperimentalVerification
import com.adjectivecolournoun.protobuf.experiments.Experiments.Hypothesis
import org.springframework.stereotype.Service

@Service
class LaboratoryService {

    ExperimentalVerification performExperiment(Hypothesis hypothesis) {
        ExperimentalVerification.newBuilder().setId(hypothesis.id).build()
    }
}
