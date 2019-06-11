package hr.fer.oop.meteo.net;

import java.util.*;

import hr.fer.oop.meteo.entity.Place;

public interface RestInterface {

    public Place getPlaceById(Integer id);

    public List<String> getPlacesByDate(String date);

    public List<String> getPlacesByDates(String date1, String date2);

    public Map<String,Double> getWeatherByDate(String place, String date);

    public Map<String,Double> getWeatherByDates(String place, String date1, String date2);

    public Place newPlace(Place placeResource);

    public Place newPlaces(String date);

    public Place newPlaces(String date1, String date2);

    public boolean updatePlace(Integer id, Place placeResource);

    public boolean deletePlace(Integer id);

}
