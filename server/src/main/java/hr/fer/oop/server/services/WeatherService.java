package hr.fer.oop.server.services;

import java.util.List;

import hr.fer.oop.server.entities.Place;

public interface WeatherService {
    List<Place> getWeatherByDate(String place, String date);

    List<Place> getWeatherByDates(String place, String date1, String date2);
}
