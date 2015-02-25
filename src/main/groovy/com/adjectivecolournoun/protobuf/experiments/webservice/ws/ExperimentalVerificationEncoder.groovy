package com.adjectivecolournoun.protobuf.experiments.webservice.ws

import com.adjectivecolournoun.protobuf.experiments.Experiments.ExperimentalVerification

import javax.websocket.EncodeException
import javax.websocket.Encoder
import javax.websocket.EndpointConfig
import java.nio.ByteBuffer

class ExperimentalVerificationEncoder implements Encoder.Binary<ExperimentalVerification> {

    @Override
    ByteBuffer encode(ExperimentalVerification verification) throws EncodeException {
        ByteBuffer.wrap verification.toByteArray()
    }

    @Override
    void init(EndpointConfig endpointConfig) {
    }

    @Override
    void destroy() {
    }
}
