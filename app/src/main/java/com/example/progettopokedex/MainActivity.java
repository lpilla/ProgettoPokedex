package com.example.progettopokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    FragmentContainerView fragmentContainerView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        fragmentContainerView = findViewById(R.id.fragment_container);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        showFragment(PokemonList.class);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.nav_pokemon_list){
                return showFragment(PokemonList.class);
            } else if(item.getItemId() == R.id.nav_pokemon_favourites){
                return showFragment(FavouritesList.class);
            }
            return false;
        });
    }
    protected Boolean showFragment(Class<? extends Fragment> theClass){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,theClass,null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
        return true;
    }
}