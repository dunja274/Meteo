package hr.fer.oop.meteo.activites;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

import hr.fer.oop.meteo.CitiesAdapter;
import hr.fer.oop.meteo.R;
import hr.fer.oop.meteo.util.Clock;

public class MainActivity extends AppCompatActivity {

    // TODO(Dino) : New screen for graph drawing
    // TODO(Dino) : List of cities under the buttons
    // Application main entry point
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // First date picker
        final TextView date = findViewById(R.id.chose_date_string);
        final Button choseDate = findViewById(R.id.chose_date);

        // Second date picker
        final TextView date2 = findViewById(R.id.chose_date_string2);
        final Button choseDate2 = findViewById(R.id.chose_date2);

        // Range picker ( ON - Range || OFF - Date )
        final Switch rangeOn = findViewById(R.id.range);

        final Button request = findViewById(R.id.request);

        // City list
        final Set<String> citiesSet = new HashSet<>();
        final String[] cities = citiesSet.toArray(new String[citiesSet.size()]);

        final ListView listCity = findViewById(R.id.cities);
        CitiesAdapter ca = new CitiesAdapter(getApplicationContext(), cities);
        listCity.setAdapter(ca);

        final Clock clkDate1 = new Clock();
        clkDate1.setDate(0, 0, 0);
        final Clock clkDate2 = new Clock();
        clkDate2.setDate(0, 0, 0);

        // Listener for click on the Date #1
        choseDate.setOnClickListener((View v) -> {
            Clock clk = new Clock();

            // Dialog with calendar for the first date
            final DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (DatePicker view, int year, int month, int dayOfMonth) -> {
                        date.setText(String.format("%s.%s.%s", dayOfMonth, month, year));
                        choseDate2.setEnabled(true);
                        clkDate1.setDate(year, month, dayOfMonth);  // Remember the date #
                    }, clk.getYear(), clk.getMonth(), clk.getDay());
            datePickerDialog.getDatePicker().setMaxDate(clk.getDateInMillis());
            datePickerDialog.show();    // Show calendar dialog
            request.setVisibility(View.VISIBLE);
        });

        // Same listener for Date #2
        choseDate2.setOnClickListener((View v) -> {

                Clock clk = new Clock();

                final DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        (DatePicker view, int year, int month, int dayOfMonth) ->  {
                                date2.setText(String.format("%s.%s.%s", dayOfMonth, month, year));
                                clkDate2.setDate(year, month, dayOfMonth);
                        }, clk.getYear(), clk.getMonth(), clk.getDay());
                // Set Min date so the second date needs to be newe
                datePickerDialog.getDatePicker().setMinDate(clkDate1.getDateInMillis());
                datePickerDialog.getDatePicker().setMaxDate(clk.getDateInMillis());
                datePickerDialog.show();
        });

        // Listener for switch in the corner
        rangeOn.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            // Switch (ON - range is picked, OFF - only one date is picked)
            if (isChecked) {
                date2.setVisibility(View.VISIBLE);
                choseDate2.setVisibility(View.VISIBLE);
            } else {
                date2.setVisibility(View.INVISIBLE);
                choseDate2.setVisibility(View.INVISIBLE);
            }
        });

        request.setOnClickListener((View v) -> {
            for (Integer i = 0; i < 25; i++) {
                citiesSet.add(i.toString());
            }
            ca.setCities(citiesSet);
            listCity.setAdapter(ca);
            citiesSet.clear();
        });

        listCity.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Intent chosenCityActivity = new Intent(MainActivity.this, ChosenCityActivity.class);
            String chosenCity = ca.getItem(position);
            chosenCityActivity.putExtra("chosenCity", chosenCity);
            startActivity(chosenCityActivity);
        });

    }

}
