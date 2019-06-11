package hr.fer.oop.meteo.net;

import java.util.List;
import java.util.*;
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

    @GET("/api/weather/{place}/{date}")
    public Map<String,Double> getWeatherByDate(@Path("place") String place,
                                                       @Path("date")String date);

    @GET("/api/weather/{place}/{date1}/{date2}")
    public Map<String,Double> getWeatherByDates(@Path("place") String place,
                                                @Path("date1") String date1,
                                                @Path("date2") String date2);

    @POST("/api/place/")
    public Place newPlace(@Body Place placeResource);

    @POST("/api/places/{date}")
    public Place newPlaces(@Path("date") String date);

    @POST("/api/places/{date1}/{date2}")
    public Place newPlaces(@Path("date1") String date1,
                                    @Path("date2") String date2);

    @PUT("/api/place/{id}")
    public boolean updatePlace(@Path("id") Integer id,
                                      @Body Place placeResource);

    @DELETE("/api/place/{id}")
    public boolean deletePlace(@Path("id") Integer id);
}
