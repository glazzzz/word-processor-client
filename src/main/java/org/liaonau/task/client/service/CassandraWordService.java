package org.liaonau.task.client.service;

import com.datastax.oss.driver.internal.core.cql.DefaultRow;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("cassandra")
public class CassandraWordService implements WordService {

    public static final String CASSANDRA_SEARCH_URI = "{{search.cassandra-uri}}";
    public static final String CONSUMER_KAFKA_URI = "{{consumer.kafka-uri}}";
    private ProducerTemplate producerTemplate;

    @Autowired
    public void setProducerTemplate(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @Override
    public void send(String word) {
        producerTemplate.sendBody(CONSUMER_KAFKA_URI, word);
    }

    @Override
    public List<String> search(String word) {
        List<DefaultRow> searchResults = producerTemplate.requestBody(CASSANDRA_SEARCH_URI, "%" + word + "%", ArrayList.class);
        List<String> result = searchResults.stream().map(element -> element.getString(1)).collect(Collectors.toList());
        return result;
    }

    @Override
    public boolean isWordValid(@NotNull String word) {
        return !word.matches("^.*[^a-zA-Z0-9 ].*$");
    }
}
