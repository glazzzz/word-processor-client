package org.liaonau.words.client.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddWordRq {
    @NonNull
    private String word;
}
