package hr.fer.oop.meteo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

// TODO(Dino) : Clean up and work on Class
public class CitiesAdapter extends BaseAdapter {
    final private String[] countryList;
    final private LayoutInflater inflater;

    public CitiesAdapter(Context applicationContext, String[] countryList) {
        this.countryList = countryList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_listview, null);
        TextView country = view.findViewById(R.id.textView);
        country.setText(countryList[i]);
        return view;
    }
}
