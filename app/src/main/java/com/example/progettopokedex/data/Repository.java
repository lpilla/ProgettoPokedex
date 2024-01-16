package com.example.progettopokedex.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.progettopokedex.controller.AppController;
import com.example.progettopokedex.models.PokemonShortResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Repository {

    public void getPosts(final PostAsyncResponse callback){
        String urlPost ="https://pokeapi.co/api/v2/pokemon?limit=10&offset=0";

        ArrayList<PokemonShortResponse> pokemons = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,urlPost,null, response -> {
            try{
                JSONArray results = response.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject pokemonObject = results.getJSONObject(i);
                    String pkmName = pokemonObject.getString("name");
                    String pkmUrl = pokemonObject.getString("url");
                    PokemonShortResponse pkm = new PokemonShortResponse(pkmName,pkmUrl);
                    pokemons.add(pkm);
                }
                callback.processoterminato(pokemons);

            } catch (Exception e) {
                e.printStackTrace();
                callback.processoFallito(e);
            }
        }, error -> {
            Log.d("Main","Error");
        });

        AppController.getInstance().addToRequestQueue(request);
    }
}
