package org.liaonau.task.client.rest;

import org.liaonau.task.client.model.AddWordRq;
import org.liaonau.task.client.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*")
@Validated
public class WordRestCtrl {

    private WordService wordService;

    @Autowired
    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping(path = "/word")
    public void addWord(@RequestBody @Validated AddWordRq addWordRequest) {
        wordService.send(addWordRequest.getWord());
    }

    @GetMapping(path = "/word")
    public List<String> findWord(@RequestParam String text) {
        return wordService.search(text);
    }
}
