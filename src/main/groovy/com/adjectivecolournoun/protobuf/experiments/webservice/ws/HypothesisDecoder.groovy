package com.adjectivecolournoun.protobuf.experiments.webservice.ws

import com.adjectivecolournoun.protobuf.experiments.Experiments
import com.adjectivecolournoun.protobuf.experiments.Experiments.Hypothesis
import com.google.protobuf.InvalidProtocolBufferException

import javax.websocket.DecodeException
import javax.websocket.Decoder
import javax.websocket.EndpointConfig
import java.nio.ByteBuffer

class HypothesisDecoder implements Decoder.Binary<Hypothesis> {

    @Override
    Experiments.Hypothesis decode(ByteBuffer bytes) throws DecodeException {
        try {
            decodeHypothesis bytes
        } catch (InvalidProtocolBufferException e) {
            throw new DecodeException(bytes, 'Invalid Hypothesis', e)
        }
    }

    @Override
    boolean willDecode(ByteBuffer bytes) {
        try {
            decodeHypothesis bytes
            true
        } catch (InvalidProtocolBufferException e) {
            false
        }
    }

    private Hypothesis decodeHypothesis(ByteBuffer bytes) {
        Hypothesis.parseFrom(bytes.array())
    }

    @Override
    void init(EndpointConfig endpointConfig) {
    }

    @Override
    void destroy() {
    }
}
