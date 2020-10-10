package org.liaonau.task.client;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@MockEndpoints
public class ClientApplicationTest {
    @DirtiesContext
    @Test
    void contextLoads() {
    }

    @Produce("{{producer.kafka-uri}}")
    private ProducerTemplate producerTemplate;

    @EndpointInject("{{consumer.kafka-uri}}")
    private MockEndpoint mockKafka;

    @EndpointInject("{{producer.cassandra-uri}}")
    private MockEndpoint mockCassandra;

}
