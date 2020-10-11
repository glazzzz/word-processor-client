package org.liaonau.task.client.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.liaonau.task.client.model.AddWordRq;
import org.liaonau.task.client.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
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
    @Operation(summary = "Add word to processing queue", description = "Add word to processing queue",
    responses = {
        @ApiResponse(responseCode = "200", description = "Word successfully sent to processing queue"),
//        @ApiResponse(responseCode = "400", description = "Word should be alphanumeric")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", examples = {
            @ExampleObject(summary = "Request to add word to sentence", name = "Request", value = "{\"word\":\"Hello\"}"
            )
    }))
    public void addWord(@RequestBody @Validated AddWordRq addWordRequest) {
        if (!wordService.isWordValid(addWordRequest.getWord())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Word must be alphanumeric");
        }
        wordService.send(addWordRequest.getWord());
    }

    @GetMapping(path = "/word")
    @Operation(summary = "Search for word", description = "Search for word in existing sentences",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All sentences that contain required word in JSON format",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
//                    @ApiResponse(responseCode = "400", description = "Word should be alphanumeric")
            })
    @Parameters({
            @Parameter(name = "text", in = ParameterIn.QUERY, description = "search request")
    })
    public List<String> findWord(@RequestParam @NotNull String text) {
        if (!wordService.isWordValid(text)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Word must be alphanumeric");
        }
        return wordService.search(text);
    }
}
