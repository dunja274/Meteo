package hr.fer.oop.server.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import javax.transaction.Transactional;

import hr.fer.oop.server.entities.Place;
import hr.fer.oop.server.respositories.PlaceRepository;
import hr.fer.oop.server.services.PlaceService;

@Service
public class PlaceServiceJpaImpl implements PlaceService {
    @Autowired
    private PlaceRepository repo;

    @Override
    public Iterable<Place> findAll() {
        return repo.findAll();
    }

    @Override
    public Place getWithId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Place save(Place place) {
        return repo.save(place);
    }

    @Override
    @Transactional
    public void update(Place place) {
        if (repo.existsById(place.getId())) {
            repo.save(place);
        } else {
            throw new RuntimeException("Place doesn't exist but update was called");
        }
    }

    @Override
    @Transactional
    public void delete(String id) {
        repo.deleteById(Integer.parseInt(id));
    }

    @Override
    public Set<Place> getByDate(String date) {
        return repo.getByDate(date);
    }

    @Override
    public Set<Place> getByDates(String date1, String date2) {
        return repo.getByDates(date1, date2);
    }
}
