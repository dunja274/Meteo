package hr.fer.oop.meteo.activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.fer.oop.meteo.R;
import hr.fer.oop.meteo.RainfallAdapter;
import hr.fer.oop.meteo.util.Clock;


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

        BarChart chart = findViewById(R.id.chart);

        Clock c1 = new Clock(2018,5,5);

        Map<String, Double> data = new HashMap<>();
        data.put(c1.toString(), 2.03);    c1.addDays(1);
        data.put(c1.toString(), 0.00);    c1.addDays(1);
        data.put(c1.toString(), 8.12);    c1.addDays(1);
        data.put(c1.toString(), 3.03);    c1.addDays(1);

        final ListView rainfallList = findViewById(R.id.rainfall);
        RainfallAdapter ra = new RainfallAdapter(this, data);
        rainfallList.setAdapter(ra);

        List<BarEntry> entries = new ArrayList<>();

        Integer i = 0;
        for (Map.Entry<String, Double> e : data.entrySet()) {
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
}
