package hr.fer.oop.meteo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Set;

// TODO(Dino) : Clean up and work on Class
public class CitiesAdapter extends BaseAdapter {
    private String[] cities;
    final private LayoutInflater inflater;

    public CitiesAdapter(Context applicationContext, String[] countryList) {
        this.cities = countryList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return cities.length;
    }

    @Override
    public String getItem(int i) {
        return cities[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setCities(Set<String> cities) {
        this.cities = cities.toArray(new String[cities.size()]);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_listview, null);
        TextView country = view.findViewById(R.id.textView);
        country.setText(cities[i]);
        return view;
    }
}
