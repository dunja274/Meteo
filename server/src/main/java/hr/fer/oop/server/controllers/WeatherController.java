package hr.fer.oop.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import hr.fer.oop.server.entities.Place;
import hr.fer.oop.server.services.WeatherService;

import static hr.fer.oop.server.resource.RepresentationUtility.throwExceptionIfListNull;

//TODO(Dino) : Add set functions (Weather)

@RestController
public class WeatherController {
    private final WeatherService weatherService;
    private final WeatherAssembler weatherAssembler;

    @Autowired
    public WeatherController(WeatherService weatherService, WeatherAssembler weatherAssembler) {
        this.weatherService = weatherService;
        this.weatherAssembler = weatherAssembler;
    }

    @GetMapping(value = "/api/weather/{place}/{date}")
    public Map<String, Double> getWeatherByDate(@PathVariable(value="place") String place,
                                                @PathVariable(value="date") String date) {
        List<Place> weather = throwExceptionIfListNull(
                weatherService.getWeatherByDate(place, date));
        return weatherAssembler.toResourceMap(weather);
    }

    @GetMapping(value = "/api/weather/{place}/{date1}/{date2}")
    public Map<String, Double> getWeatherByDate(@PathVariable(value="place") String place,
                                                @PathVariable(value="date1") String date1,
                                                @PathVariable(value="date2") String date2){
        List<Place> weather = throwExceptionIfListNull(
                weatherService.getWeatherByDates(place, date1, date2));
        return weatherAssembler.toResourceMap(weather);
    }
}
