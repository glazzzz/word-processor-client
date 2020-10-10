package org.liaonau.words.client.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ClientRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("{{producer.kafka-uri}}").log("Adding new sentence - ${body}").to("{{producer.cassandra-uri}}");
    }
}
