package com.example.progettopokedex.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettopokedex.R;
import com.example.progettopokedex.models.PokemonShortResponse;
import com.example.progettopokedex.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder> {
    private final ArrayList<PokemonShortResponse> pokemons;

    public PokemonListAdapter(ArrayList<PokemonShortResponse> pokemons) {
        this.pokemons = pokemons;
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

        public PokemonListViewHolder(@NonNull View itemView) {
            super(itemView);

            pokemonListItemImage = itemView.findViewById(R.id.pokemon_list_item_image);
            pokemonListItemTitle = itemView.findViewById(R.id.pokemon_list_item_title);
        }

        public void bind(PokemonShortResponse pokemon) {
            pokemonListItemTitle.setText(pokemon.getName());
            String pokemonImage = Utils.getUrlImageOfPokemon(pokemon.getId());
            Picasso.get().load(pokemonImage).resize(150,0).into(pokemonListItemImage);
        }
    }




}
