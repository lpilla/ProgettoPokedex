package com.example.progettopokedex.models;

import junit.framework.TestCase;

public class PokemonShortResponseTest extends TestCase {

    public void testPokemonConstructor(){
        PokemonShortResponse pk = new PokemonShortResponse("bulbasaur","https://pokeapi.co/api/v2/pokemon/1/");
        assertEquals(pk.getId(),1);
    }

}