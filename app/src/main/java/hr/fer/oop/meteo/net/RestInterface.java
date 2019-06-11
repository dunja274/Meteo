package hr.fer.oop.meteo.net;

import java.util.*;

import hr.fer.oop.meteo.entity.Place;

public interface RestInterface {

    Place getPlaceById(Integer id);

    List<String> getPlacesByDate(String date);

    List<String> getPlacesByDates(String date1, String date2);

    Map<String, Double> getWeatherByDate(String place, String date);

    Map<String, Double> getWeatherByDates(String place, String date1, String date2);

    Place newPlace(Place placeResource);

    Place newPlaces(String date);

    Place newPlaces(String date1, String date2);

    boolean updatePlace(Integer id, Place placeResource);

    boolean deletePlace(Integer id);

}
