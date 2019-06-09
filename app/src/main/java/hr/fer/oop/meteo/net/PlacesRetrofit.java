package hr.fer.oop.meteo.net;

import java.util.List;

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

    // TODO(Dunja) : add POST void functions

}
