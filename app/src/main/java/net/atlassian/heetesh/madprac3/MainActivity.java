package net.atlassian.heetesh.madprac3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fm = getSupportFragmentManager();

        // Selector fragment
        SelectorFragment selectorFragment = (SelectorFragment) fm.findFragmentById(R.id.fragment_selector_holder);
        if (selectorFragment == null) {
            selectorFragment = new SelectorFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_selector_holder, selectorFragment) // adding fragment in frame layout holder
                    .commit(); // commits the transaction
        }


        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.fragment_map_holder);
        if (mapFragment == null) {
            mapFragment = new MapFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_map_holder, mapFragment) // adds to the frame layout holder
                    .commit(); // commits the map fragment
        }


    }
}