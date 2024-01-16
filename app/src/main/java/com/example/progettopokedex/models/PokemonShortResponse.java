package com.example.progettopokedex.models;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "pokemon_table")
public class PokemonShortResponse implements Serializable {
    @PrimaryKey
    private int id;
    private String name;
    private String url;
    private boolean favourite;

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public PokemonShortResponse(String name, String url) {
        this.name = name;
        this.url = url;
        this.favourite = false;
        int lastSlashIndex = url.lastIndexOf('/');
        String urlSenzaSlash=url.substring(0,lastSlashIndex);
        lastSlashIndex = urlSenzaSlash.lastIndexOf('/');
        String idString = urlSenzaSlash.substring(lastSlashIndex+1);
        setId(Integer.parseInt(idString));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PokemonShortResponse{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }
}