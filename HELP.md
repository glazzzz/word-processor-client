# Getting Started

### Reference Documentation
Please build docker image in case if you are planning to use Docker to launch system
 
 ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=words/client
 
 Then check if you already build image for processing module from https://github.com/glazzzz/word-processor and follow instructions from https://github.com/glazzzz/word-to-sentence-procesor
 
There are two Spring profiles that enable different search implementations:
- elasticsearch - use Elasticsearch as a backend for search. Works only for whole words. Search will return all sentences that contain provided word.
- cassandra - use Cassandra as a backend for search. Search will return all sentences that contain provided word or its part. 
 
 