package hr.fer.oop.meteo.activites;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import hr.fer.oop.meteo.R;
import hr.fer.oop.meteo.adapters.RainfallAdapter;
import hr.fer.oop.meteo.util.Clock;

import hr.fer.oop.meteo.net.RestFactory;
import hr.fer.oop.meteo.net.RestInterface;


public class ChosenCityActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_city);

        String chosenCity = getIntent().getStringExtra("chosenCity");
        String date1 = getIntent().getStringExtra("date1");
        String date2 = getIntent().getStringExtra("date2");

        final Toolbar toolbar = findViewById(R.id.city_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(chosenCity);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener((View v) -> finish());

        BarChart chart = findViewById(R.id.chart);

        String[] date1s = date1.split("-");
        Clock c1 = new Clock(Integer.parseInt(date1s[0]), Integer.parseInt(date1s[1]) - 1, Integer.parseInt(date1s[2]));

        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Map<String, Double>> task = new AsyncTask<Void, Void, Map<String, Double>>() {

            @Override
            protected Map<String, Double> doInBackground(Void... params) {
                RestInterface rest = RestFactory.getInstance();
                if (date2 != null) {
                    return rest.getWeatherByDates(chosenCity, date1, date2);
                } else {
                    return rest.getWeatherByDate(chosenCity, date1);
                }
            }

            @Override
            protected void onPostExecute(Map<String, Double> data) {
                final ListView rainfallList = findViewById(R.id.rainfall);
                RainfallAdapter ra = new RainfallAdapter(ChosenCityActivity.this, data);
                rainfallList.setAdapter(ra);

                Clock temp = new Clock(c1);
                if (date2 != null) {
                    String[] date2s = date2.split("-");
                    Clock c2 = new Clock(Integer.parseInt(date2s[0]), Integer.parseInt(date2s[1]) - 1, Integer.parseInt(date2s[2]));
                    while (!c1.toString().equals(c2.toString())) {
                        if (!data.containsKey(c1.toString())) {
                            data.put(c1.toString(), 0.0);
                        }
                        c1.addDays(1);
                    }
                }

                TreeMap<String, Double> sorted = new TreeMap<>(data);

                List<BarEntry> entries = new ArrayList<>();
                Integer i = 0;
                for (Map.Entry<String, Double> e : sorted.entrySet()) {
                    entries.add(new BarEntry(i.floatValue(), e.getValue().floatValue(), e.getKey()));
                    i++;
                }

                BarDataSet dataSet = new BarDataSet(entries, "Količina oborina l/m²");
                BarData bd = new BarData(dataSet);

                chart.getXAxis().setDrawLabels(false);
                chart.setDrawBarShadow(false);
                chart.getDescription().setEnabled(false);
                chart.setPinchZoom(false);
                chart.setDrawGridBackground(false);
                chart.setDrawValueAboveBar(true);
                chart.setData(bd);
                chart.invalidate();

            }
        };
        task.execute();
    }
}
