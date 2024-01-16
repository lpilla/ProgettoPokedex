package com.example.progettopokedex.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.progettopokedex.dao.PokemonDao;
import com.example.progettopokedex.models.PokemonShortResponse;

@Database(entities = {PokemonShortResponse.class}, version = 1)
public abstract class PokemonDatabase extends RoomDatabase {
    public abstract PokemonDao pokemonDao();

    // Singleton pattern to ensure only one instance of the database is created.
    private static volatile PokemonDatabase INSTANCE;

    public static PokemonDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PokemonDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    PokemonDatabase.class,
                                    "pokemon_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}