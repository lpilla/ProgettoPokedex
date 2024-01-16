package com.example.progettopokedex.adapters;

import android.app.Application;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettopokedex.R;
import com.example.progettopokedex.SinglePokemon;
import com.example.progettopokedex.data.PokemonRepository;
import com.example.progettopokedex.models.PokemonShortResponse;
import com.example.progettopokedex.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder> {
    private final ArrayList<PokemonShortResponse> pokemons;
    private static PokemonRepository pokemonRepository; // Add reference to the repository


    public PokemonListAdapter(ArrayList<PokemonShortResponse> pokemons, Application application) {
        this.pokemons = pokemons;
        this.pokemonRepository = new PokemonRepository(application);
    }

    public PokemonListViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokem_list_item,parent,false);
        return new PokemonListViewHolder(view);
    }

    public void onBindViewHolder(PokemonListViewHolder holder, int position){
        holder.bind(pokemons.get(position));
    }

    public int getItemCount(){
        return pokemons.size();
    }

    static class PokemonListViewHolder extends RecyclerView.ViewHolder{

        private ImageView pokemonListItemImage;
        private TextView pokemonListItemTitle;
        private ImageView bookmarkImage;

        public PokemonListViewHolder(@NonNull View itemView) {
            super(itemView);

            pokemonListItemImage = itemView.findViewById(R.id.pokemon_list_item_image);
            pokemonListItemTitle = itemView.findViewById(R.id.pokemon_list_item_title);
            bookmarkImage = itemView.findViewById(R.id.bookmarkImage);
        }

        public void bind(PokemonShortResponse pokemon) {
            pokemonListItemTitle.setText(pokemon.getName());

            // Check if the Pokemon is a favorite by querying the database
            LiveData<List<PokemonShortResponse>> favoritesLiveData = pokemonRepository.getAllPokemon();
            favoritesLiveData.observe((LifecycleOwner) itemView.getContext(), pokemonList -> {
                boolean isFavorite = isPokemonInList(pokemon, pokemonList);
                if (isFavorite) {
                    bookmarkImage.setImageResource(R.drawable.bookmark_full);
                } else {
                    bookmarkImage.setImageResource(R.drawable.bookmark_rounded);
                }
                favoritesLiveData.removeObservers((LifecycleOwner) itemView.getContext());
            });

            String pokemonImage = Utils.getUrlImageOfPokemon(pokemon.getId());

            pokemonListItemTitle.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), SinglePokemon.class);
                intent.putExtra("pokemon",pokemon);
                itemView.getContext().startActivity(intent);
            });

            bookmarkImage.setOnClickListener(v -> {
                pokemon.setFavourite(!pokemon.isFavourite());
                if (pokemon.isFavourite()){
                    bookmarkImage.setImageResource(R.drawable.bookmark_full);
                    pokemonRepository.insertPokemon(pokemon);
                } else{
                    bookmarkImage.setImageResource(R.drawable.bookmark_rounded);
                    pokemonRepository.deletePokemon(pokemon);
                }
            });

            Picasso.get().load(pokemonImage).resize(150,0).into(pokemonListItemImage);
        }

        // Helper method to check if the Pokemon is in the list
        private boolean isPokemonInList(PokemonShortResponse pokemon, List<PokemonShortResponse> pokemonList) {
            for (PokemonShortResponse p : pokemonList) {
                if (p.getId() == pokemon.getId()) {
                    return true;
                }
            }
            return false;
        }

    }




}
