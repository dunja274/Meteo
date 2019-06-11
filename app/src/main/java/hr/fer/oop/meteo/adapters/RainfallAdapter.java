package hr.fer.oop.meteo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hr.fer.oop.meteo.R;

// TODO(Dino) : Clean up and work on Class
public class RainfallAdapter extends BaseAdapter {
    private String[] rainfall;
    private String[] dates;
    final private LayoutInflater inflater;

    public RainfallAdapter(Context applicationContext, Map<String, Double> rainfallMap) {
        //  String array of dates
        Set<String> dateSet = rainfallMap.keySet();
        this.dates = dateSet.toArray(new String[dateSet.size()]);

        //  String array of rainfall
        List<Double> rainfallListDouble = new ArrayList<>(rainfallMap.values());
        List<String> rainfallList = new ArrayList<>();

        for (Double r : rainfallListDouble) rainfallList.add(r.toString());

        this.rainfall = rainfallList.toArray(new String[rainfallList.size()]);
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return rainfall.length;
    }

    @Override
    public String getItem(int i) {
        return rainfall[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setCities(Set<String> rainfallSet) {
        this.rainfall = rainfallSet.toArray(new String[rainfallSet.size()]);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_listview, null);

        TextView rainView = view.findViewById(R.id.additionalTextView);
        rainView.setText(rainfall[i]);

        TextView dateView = view.findViewById(R.id.textView);
        dateView.setText(dates[i]);

        return view;
    }
}
