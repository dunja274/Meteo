package hr.fer.oop.meteo.util;

import org.springframework.http.ResponseEntity;

import java.util.Set;

import hr.fer.oop.meteo.RestFactory;
import hr.fer.oop.meteo.RestInterface;
import hr.fer.oop.meteo.entity.PlaceResource;
import retrofit.*;

public class PlacesRetrofit implements RestInterface {

    private final String baseURL;
    private final PlacesRetrofitService service;

    public PlacesRetrofit() {
        baseURL = "http://" + RestFactory.BASE_IP + ":8080/";
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint(baseURL)
                .build();

        service = retrofit.create(PlacesRetrofitService.class);
    }

    @Override
    public PlaceResource getPlaceById(Integer id){
        return service.getPlaceById(id);
    }

    @Override
    public Set<String> getPlacesByDate(String date){
            return service.getPlacesByDate(date);
    }

    @Override
    public Set<String> getPlacesByDates(String date1, String date2){
        return service.getPlacesByDates(date1, date2);
    }

    @Override
    public ResponseEntity newPlace(PlaceResource placeResource){
        return service.newPlace(placeResource);
    }

    @Override
    public ResponseEntity newPlaces(String date){
        return service.newPlaces(date);
    }

    @Override
    public ResponseEntity newPlaces(String date1, String date2){
        return service.newPlaces(date1,date2);
    }

    @Override
    public ResponseEntity updatePlace(Integer id, PlaceResource placeResource){
        return service.updatePlace(id, placeResource);
    }

    @Override
    public ResponseEntity deletePlace(Integer id){
        return service.deletePlace(id);
    }

}
