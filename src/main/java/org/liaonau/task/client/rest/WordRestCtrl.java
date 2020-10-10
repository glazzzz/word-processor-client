package org.liaonau.task.client.rest;

import org.liaonau.task.client.model.AddWordRq;
import org.liaonau.task.client.service.WordProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*")
@Validated
public class WordRestCtrl {

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
