package com.example.progettopokedex.data;


import com.example.progettopokedex.models.PokemonShortResponse;

import java.util.ArrayList;

public interface PostAsyncResponse {
    void processoterminato(ArrayList<PokemonShortResponse> pokemons);
    void processoFallito(Exception e);
}
