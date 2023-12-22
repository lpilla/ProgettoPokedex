package com.example.progettopokedex.data;

import com.example.progettopokedex.models.PokemonShortResponse;

import junit.framework.TestCase;

import java.util.ArrayList;

public class RepositoryTest extends TestCase {

    public void testRepository() {
        Repository rp = new Repository();
        rp.getPosts(new PostAsyncResponse() {
            @Override
            public void processoterminato(ArrayList<PokemonShortResponse> pokemons) {
                for (PokemonShortResponse p :
                        pokemons) {
                    System.out.println(p);
                }
            }

            @Override
            public void processoFallito(Exception e) {

            }
        });
    }

}