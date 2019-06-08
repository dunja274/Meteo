package hr.fer.oop.server.services;

import java.util.Set;

import hr.fer.oop.server.entities.Place;
import hr.fer.oop.server.resource.PlaceResource;

public interface PlaceService {

    Iterable<Place> findAll();

    Place getWithId(Integer id);

    Place save(Place place);

    Iterable<Place> saveAll(Iterable<Place> places);

    void update(Place place);

    void delete(Integer id);

    Set<Place> getByDate(String date);

    Set<Place> getByDates(String date1, String date2);

    boolean exists(PlaceResource place);

}
