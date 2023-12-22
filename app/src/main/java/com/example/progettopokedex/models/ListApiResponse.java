package com.example.progettopokedex.models;

import java.util.List;

public class ListApiResponse {
    private Long count;
    private String next;
    private String previous;
    private List<PokemonShortResponse> results;

    public ListApiResponse(Long count, String next, String previous, List<PokemonShortResponse> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }
}



