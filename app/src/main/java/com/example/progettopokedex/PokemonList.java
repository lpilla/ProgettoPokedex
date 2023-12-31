package com.example.progettopokedex;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.progettopokedex.adapters.PokemonListAdapter;
import com.example.progettopokedex.data.PostAsyncResponse;
import com.example.progettopokedex.data.Repository;
import com.example.progettopokedex.models.PokemonShortResponse;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokemonList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokemonList extends Fragment {
    String[] mioArray = {"pippo","pluto","paperino","minny","pippo","pluto","paperino","minny","pippo","pluto","paperino","minny","pippo","pluto","paperino","minny","pippo","pluto","paperino","minny"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PokemonList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PokemonList.
     */
    // TODO: Rename and change types and number of parameters
    public static PokemonList newInstance(String param1, String param2) {
        PokemonList fragment = new PokemonList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        this.getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View contentView = inflater.inflate(R.layout.fragment_pokemon_list, container, false);


        new Repository().getPosts(new PostAsyncResponse() {
            @Override
            public void processoterminato(ArrayList<PokemonShortResponse> pokemons) {
                RecyclerView listView = contentView.findViewById(R.id.pokemonListView);
                PokemonListAdapter arrayAdapter = new PokemonListAdapter(pokemons);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void processoFallito(Exception e) {
                Log.d("Errore", e.getMessage());
            }
        });

        return  contentView;
    }
}