package hr.fer.oop.meteo.activites;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import hr.fer.oop.meteo.R;


public class ChosenCityActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_city);

        String chosenCity = getIntent().getStringExtra("chosenCity");

        final Toolbar toolbar = findViewById(R.id.city_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(chosenCity);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
