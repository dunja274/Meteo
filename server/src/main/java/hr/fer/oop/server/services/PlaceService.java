package hr.fer.oop.server.services;

import java.util.Set;

import hr.fer.oop.server.entities.Place;

public interface PlaceService {

    Iterable<Place> findAll();

    Place getWithId(Integer id);

    Place save(Place place);

    void update(Place place);

    void delete(String id);

    Set<Place> getByDate(String date);

    Set<Place> getByDates(String date1, String date2);
}
