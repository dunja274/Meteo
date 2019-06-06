package hr.fer.oop.server.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import hr.fer.oop.server.entities.Place;
import hr.fer.oop.server.respositories.WeatherRepository;
import hr.fer.oop.server.services.WeatherService;

@Service
public class WeatherServiceJpaImpl implements WeatherService {
    @Autowired
    private WeatherRepository repo;

    @Override
    public List<Place> getWeatherByDate(String place, String date) {
        return repo.getWeatherByDate(place, date);
    }

    @Override
    public List<Place> getWeatherByDates(String place, String date1, String date2) {
        return repo.getWeatherByDates(place, date1, date2);
    }
}
