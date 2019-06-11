package hr.fer.oop.meteo.net;

import java.util.List;
import java.util.*;

import hr.fer.oop.meteo.entity.Place;
import retrofit.*;

public class PlacesRetrofit implements RestInterface {

    private final String baseURL;
    private final PlacesService service;

    public PlacesRetrofit() {
        baseURL = "http://" + RestFactory.BASE_IP + ":8080";
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint(baseURL)
                .build();

        service = retrofit.create(PlacesService.class);
    }

    @Override
    public Place getPlaceById(Integer id){
        return service.getPlaceById(id);
    }

    @Override
    public List<String> getPlacesByDate(String date){
            return service.getPlacesByDate(date);
    }

    @Override
    public List<String> getPlacesByDates(String date1, String date2){
        return service.getPlacesByDates(date1, date2);
    }

    @Override
    public Map<String,Double> getWeatherByDate(String place, String date){
        return service.getWeatherByDate(place, date);
    }

    @Override
    public Map<String,Double> getWeatherByDates(String place, String date1, String date2){
        return service.getWeatherByDates(place, date1, date2);
    }

    @Override
    public Place newPlace(Place placeResource){
        return service.newPlace(placeResource);
    }

    @Override
    public Place newPlaces(String date){
        return service.newPlaces(date);
    }

    @Override
    public Place newPlaces(String date1, String date2){
        return service.newPlaces(date1, date2);
    }

    @Override
    public boolean updatePlace(Integer id, Place placeResource){
        return service.updatePlace(id, placeResource);
    }

    @Override
    public boolean deletePlace(Integer id){
        return service.deletePlace(id);
    }

    public PlacesService getService() {
        return this.service;
    }

}
