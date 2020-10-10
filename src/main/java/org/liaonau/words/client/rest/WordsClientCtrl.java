package org.liaonau.words.client.rest;

import org.liaonau.words.client.model.AddWordRq;
import org.liaonau.words.client.service.WordProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*")
@Validated
public class WordsClientCtrl {

    private WordProducerService wordService;

    @Autowired
    public void setWordService(WordProducerService wordService) {
        this.wordService = wordService;
    }

    @PostMapping(path = "/word")
    public void addWord(@RequestBody @Validated AddWordRq addWordRequest) {
        wordService.send("{{consumer.kafka-uri}}", addWordRequest.getWord());
    }
}
