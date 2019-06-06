package hr.fer.oop.server.controllers;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.fer.oop.server.entities.Place;

@Service
public class WeatherAssembler {
    public Map<String, Double> toResourceMap(List<Place> weather) {
        Map<String, Double> resource = new HashMap<>();
        for(Place p : weather) {
            resource.put(p.getDate(), p.getRainfall());
        }
        return resource;
    }
}