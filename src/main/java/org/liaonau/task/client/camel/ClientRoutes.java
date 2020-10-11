package org.liaonau.task.client.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ClientRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //waiting for event from kafka to forward it to cassandra and ES
        from("{{producer.kafka-uri}}")
                .log("Adding new sentence - ${body}")
                .multicast()
                .to("{{producer.cassandra-uri}}"
                        , "direct:elastic");

        //use separate endpoint to add ES specific data transformations
        from("direct:elastic")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("text", exchange.getIn().getBody().toString());

                        exchange.getIn().setBody(map);
                    }
                })
                .to("{{update.elastic-uri}}");
    }
}
