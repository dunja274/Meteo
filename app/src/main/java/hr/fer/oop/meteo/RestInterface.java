package hr.fer.oop.meteo;

import org.springframework.http.ResponseEntity;

import java.util.Set;

import hr.fer.oop.meteo.entity.PlaceResource;

public interface RestInterface {

    public PlaceResource getPlaceById(Integer id);

    public Set<String> getPlacesByDate(String date);

    public Set<String> getPlacesByDates(String date1, String date2);

    public ResponseEntity newPlace(PlaceResource placeResource);

    public ResponseEntity newPlaces(String date);

    public ResponseEntity newPlaces(String date1, String date2);


    public ResponseEntity updatePlace(Integer id, PlaceResource placeResource);

    public ResponseEntity deletePlace(Integer id);
}
