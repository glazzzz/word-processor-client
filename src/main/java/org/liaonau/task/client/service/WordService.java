package org.liaonau.task.client.service;

import org.apache.camel.ProducerTemplate;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WordService {

    public static final String ELASTICSEARCH_SEARCH_URI = "{{search.elastic-uri}}";
    public static final String CONSUMER_KAFKA_URI = "{{consumer.kafka-uri}}";
    private ProducerTemplate producerTemplate;

    @Autowired
    public void setProducerTemplate(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    /**
     * Sends word to processing module
     *
     * @param word - word to be processed
     */
    public void send(String word) {
        producerTemplate.sendBody(CONSUMER_KAFKA_URI, word);
    }

    /**
     * Performs search using provided word
     * @param word - search input
     * @return - list of JSON strings with results
     */
    public List<String> search(String word){
        Map<String, Object> actualQuery = new HashMap<>();
        actualQuery.put("text", word);

        Map<String, Object> match = new HashMap<>();
        match.put("match", actualQuery);

        Map<String, Object> query = new HashMap<>();
        query.put("query", match);

        SearchHits searchHits = producerTemplate.requestBody(ELASTICSEARCH_SEARCH_URI, query, SearchHits.class);
        return Arrays.stream(searchHits.getHits())
                .map(SearchHit::getSourceAsString)
                .collect(Collectors.toList());
    }
}
