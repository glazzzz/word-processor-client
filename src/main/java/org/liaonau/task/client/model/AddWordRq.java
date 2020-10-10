package org.liaonau.task.client.model;

import org.springframework.lang.NonNull;

public class AddWordRq {

    private String word;

    @NonNull
    public String getWord() {
        return word;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }

}
