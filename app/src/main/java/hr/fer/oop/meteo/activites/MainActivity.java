package hr.fer.oop.meteo.activites;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import hr.fer.oop.meteo.R;
import hr.fer.oop.meteo.net.RestFactory;
import hr.fer.oop.meteo.net.RestInterface;
import hr.fer.oop.meteo.util.Clock;

public class MainActivity extends AppCompatActivity {

    private ListView listCity;
    private ProgressBar loadSpinner;

    Clock clkDate1 = null;
    Clock clkDate2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCity = findViewById(R.id.cities);

        final TextView date = findViewById(R.id.chose_date_string);
        final Button choseDate = findViewById(R.id.chose_date);

        final TextView date2 = findViewById(R.id.chose_date_string2);
        final Button choseDate2 = findViewById(R.id.chose_date2);

        final Switch rangeOn = findViewById(R.id.range);

        final Button request = findViewById(R.id.request);


        choseDate.setOnClickListener((View v) -> {
            Clock clk = new Clock();

            final DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (DatePicker view, int year, int month, int dayOfMonth) -> {
                        clkDate1 = new Clock(year, month + 1, dayOfMonth);
                        date.setText(clkDate1.toString());
                        choseDate2.setEnabled(true);

                        if (rangeOn.isChecked() == false) rangeOn.setVisibility(View.VISIBLE);
                    }, clk.getYear(), clk.getMonth(), clk.getDay());
            datePickerDialog.getDatePicker().setMaxDate(clk.getDateInMillis());
            datePickerDialog.show();
            request.setVisibility(View.VISIBLE);
        });

        choseDate2.setOnClickListener((View v) -> {
            Clock clk = new Clock();

            final DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (DatePicker view, int year, int month, int dayOfMonth) -> {
                        clkDate2 = new Clock(year, month + 1, dayOfMonth);
                        date2.setText(clkDate2.toString());
                        request.setEnabled(true);
                        request.setVisibility(View.VISIBLE);
                    }, clk.getYear(), clk.getMonth(), clk.getDay());
            datePickerDialog.getDatePicker().setMinDate(clkDate1.getDateInMillis());
            datePickerDialog.show();
        });

        rangeOn.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            if (isChecked) {
                if (clkDate2 == null) request.setEnabled(false);
                date2.setVisibility(View.VISIBLE);
                choseDate2.setVisibility(View.VISIBLE);
            } else {
                clkDate2 = null;
                if (clkDate1 == null) request.setEnabled(false);
                date2.setText("");
                date2.setVisibility(View.INVISIBLE);
                choseDate2.setVisibility(View.INVISIBLE);
            }
        });

        request.setOnClickListener((View v) -> {
            @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, List<String>> task = new AsyncTask<Void, Void, List<String>>() {

                @Override
                protected void onPreExecute() {
                    loadSpinner = findViewById(R.id.pBar);
                    loadSpinner.setVisibility(View.VISIBLE);
                }

                @Override
                protected List<String> doInBackground(Void... params) {
                    RestInterface rest = RestFactory.getInstance();
                    if (clkDate2 != null) {
                        // TODO(Dunja) : first call POST with one date
                        return rest.getPlacesByDates(clkDate1.toString(), clkDate2.toString());
                    } else {
                        // TODO(Dunja) : first call POST with two dates
                        return rest.getPlacesByDate(clkDate1.toString());
                    }
                }

                @Override
                protected void onPostExecute(List<String> courses) {
                    loadSpinner.setVisibility(View.INVISIBLE);
                    updatePlacesList(courses);
                }
            };
            task.execute();
        });

        listCity.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Object itemAtPosition = parent.getItemAtPosition(position);
            String place = (String) itemAtPosition;
            Intent intent = new Intent(MainActivity.this, ChosenCityActivity.class);
            intent.putExtra("chosenCity", place);
            startActivity(intent);
        });

    }

    private void updatePlacesList(List<String> places) {
        PlaceAdapter pa = new PlaceAdapter(this,
                android.R.layout.simple_list_item_1, places);
        listCity.setAdapter(pa);
    }

    private class PlaceAdapter extends ArrayAdapter<String> {
        private List<String> placeList;

        public PlaceAdapter(Context context, int textViewResourceId, List<String> places) {
            super(context, textViewResourceId, places);
            placeList = places;
        }
    }
}
