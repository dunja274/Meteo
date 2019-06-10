package hr.fer.oop.meteo.net;

import java.util.List;

import hr.fer.oop.meteo.entity.Place;

public interface RestInterface {

    public Place getPlaceById(Integer id);

    public List<String> getPlacesByDate(String date);

    public List<String> getPlacesByDates(String date1, String date2);

    public boolean newPlace(Place placeResource);

    public boolean newPlaces(String date);

    public boolean newPlaces(String date1, String date2);

    public boolean updatePlace(Integer id, Place placeResource);

    public boolean deletePlace(Integer id);

}
