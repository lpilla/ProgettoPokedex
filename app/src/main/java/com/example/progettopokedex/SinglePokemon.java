package com.example.progettopokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progettopokedex.R;
import com.example.progettopokedex.models.PokemonShortResponse;
import com.example.progettopokedex.utils.Utils;
import com.squareup.picasso.Picasso;

public class SinglePokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pokemon);

        TextView pokemonSingleName = findViewById(R.id.pokemonSingleName);
        ImageView pokemonSingleImage = findViewById(R.id.pokemonSingleImage);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        PokemonShortResponse pokemon = (PokemonShortResponse) getIntent().getSerializableExtra("pokemon");

        Picasso.get().load(Utils.getUrlImageOfPokemon(pokemon.getId())).resize(500,0).into(pokemonSingleImage);


        pokemonSingleName.setText(pokemon.getName());
    }
}