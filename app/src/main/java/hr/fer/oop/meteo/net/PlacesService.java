package hr.fer.oop.meteo.net;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

import hr.fer.oop.meteo.entity.Place;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.*;

public interface PlacesService {

    @GET("/api/place/{id}")
    public Place getPlaceById(@Path("id") Integer id);

    @GET("/api/date/{date}")
    public List<String> getPlacesByDate(@Path("date") String date);
    
    @GET("/api/dates/{date1}/{date2}")
    public List<String> getPlacesByDates(@Path("date1") String date1,
                                        @Path("date2") String date2);

    @POST("/api/place/")
    public boolean newPlace(@Body Place placeResource);

    @POST("/api/places/{date}")
    public boolean newPlaces(@Path("date") String date);

    @POST("/api/places/{date1}/{date2}")
    public boolean newPlaces(@Path("date1") String date1,
                                    @Path("date2") String date2);

    @PUT("/api/place/{id}")
    public boolean updatePlace(@Path("id") Integer id,
                                      @Body Place placeResource);

    @DELETE("/api/place/{id}")
    public boolean deletePlace(@Path("id") Integer id);
}
