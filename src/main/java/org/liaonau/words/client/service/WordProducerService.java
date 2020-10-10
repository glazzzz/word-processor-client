package org.liaonau.words.client.service;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordProducerService {

    private ProducerTemplate producerTemplate;

    @Autowired
    public void setProducerTemplate(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public void send(String endpointUri, Object payload) {
        producerTemplate.sendBody(endpointUri, payload);
    }
}
