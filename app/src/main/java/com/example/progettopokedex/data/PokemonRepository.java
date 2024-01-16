package com.example.progettopokedex.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.progettopokedex.dao.PokemonDao;
import com.example.progettopokedex.database.PokemonDatabase;
import com.example.progettopokedex.models.PokemonShortResponse;

import java.util.List;

public class PokemonRepository {
    private PokemonDao pokemonDao;

    public PokemonRepository(Application application) {
        PokemonDatabase database = PokemonDatabase.getDatabase(application);
        pokemonDao = database.pokemonDao();
    }

    public LiveData<List<PokemonShortResponse>> getAllPokemon() {
        return pokemonDao.getAllPokemon();
    }

    public void insertPokemon(PokemonShortResponse pokemon) {
        new InsertPokemonAsyncTask(pokemonDao).execute(pokemon);
    }

    public void deletePokemon(PokemonShortResponse pokemon) {
        new DeletePokemonAsyncTask(pokemonDao).execute(pokemon);
    }

    private static class InsertPokemonAsyncTask extends AsyncTask<PokemonShortResponse, Void, Void> {
        private PokemonDao pokemonDao;

        private InsertPokemonAsyncTask(PokemonDao pokemonDao) {
            this.pokemonDao = pokemonDao;
        }

        @Override
        protected Void doInBackground(PokemonShortResponse... pokemon) {
            pokemonDao.insertPokemon(pokemon[0]);
            return null;
        }
    }

    private static class DeletePokemonAsyncTask extends AsyncTask<PokemonShortResponse, Void, Void> {
        private PokemonDao pokemonDao;

        private DeletePokemonAsyncTask(PokemonDao pokemonDao) {
            this.pokemonDao = pokemonDao;
        }

        @Override
        protected Void doInBackground(PokemonShortResponse... pokemon) {
            pokemonDao.deletePokemon(pokemon[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Notify observers that data has changed (the Pokemon was deleted)
            // This ensures that the LiveData observers will be notified
            // and the UI will be updated accordingly.
            super.onPostExecute(aVoid);
        }
    }
}
