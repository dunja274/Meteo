package hr.fer.oop.meteo.net;

import java.util.List;

import hr.fer.oop.meteo.entity.Place;

public interface RestInterface {

    public Place getPlaceById(Integer id);

    public List<String> getPlacesByDate(String date);

    public List<String> getPlacesByDates(String date1, String date2);

    // TODO(Dunja) add POST functions public void setPlaces() ...
}
