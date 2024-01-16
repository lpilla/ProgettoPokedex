package com.example.progettopokedex.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.progettopokedex.models.PokemonShortResponse;

import java.util.List;

@Dao
public interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPokemon(PokemonShortResponse pokemon);

    @Query("SELECT * FROM pokemon_table WHERE id = :pokemonId")
    LiveData<PokemonShortResponse> getPokemonById(int pokemonId);

    @Query("SELECT * FROM pokemon_table")
    LiveData<List<PokemonShortResponse>> getAllPokemon();

    @Update
    void updatePokemon(PokemonShortResponse pokemon);

    @Delete
    void deletePokemon(PokemonShortResponse pokemon);

    // Add other CRUD operations as needed...
}
