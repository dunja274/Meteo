package hr.fer.oop.meteo.util;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import hr.fer.oop.meteo.entity.PlaceResource;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.*;

public interface PlacesRetrofitService {

    @GET("/api/place/{id}")
    public PlaceResource getPlaceById(@Path("id") Integer id);

    @GET("/api/date/{date}")
    public Set<String> getPlacesByDate(@Path("date") String date);

    @GET("/api/dates/{date1}/{date2}")
    public Set<String> getPlacesByDates(@Path("date1") String date1,
                                        @Path("date2") String date2);

    @POST("/api/place/")
    public ResponseEntity newPlace(@Body PlaceResource placeResource);

    @POST("/api/places/{date}")
    public ResponseEntity newPlaces(@Path("date") String date);

    @POST("/api/places/{date1}/{date2}")
    public ResponseEntity newPlaces(@Path("date1") String date1,
                                    @Path("date2") String date2);

    @PUT("/api/place/{id}")
    public ResponseEntity updatePlace(@Path("id") Integer id,
                                      @Body PlaceResource placeResource);

    @DELETE("/api/place/{id}")
    public ResponseEntity deletePlace(@Path("id") Integer id);
}
