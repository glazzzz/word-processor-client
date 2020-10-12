package org.liaonau.task.client.service;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface WordService {
    /**
     * Sends word to processing module
     *
     * @param word - word to be processed
     */
    void send(String word);

    /**
     * Performs search using provided word
     * @param word - search input
     * @return - list of JSON strings with results
     */
    List<String> search(String word);

    boolean isWordValid(@NotNull String word);
}
