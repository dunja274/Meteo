package hr.fer.oop.server.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hr.fer.oop.server.entities.Place;
import hr.fer.oop.server.resource.PlaceResource;

@Service
class PlaceAssembler {
    public PlaceResource toResource(Place place) {
        PlaceResource resource = new PlaceResource();
        BeanUtils.copyProperties(place, resource);
        return resource;
    }

    public Set<String> toResourceSet(Set<Place> places) {
        Set<String> resource = new HashSet<>();
        for (Place p : places) {
            resource.add(p.getPlace());
        }
        return resource;
    }

    public Place toEntity(PlaceResource placeResource) {
        Place entity = new Place();
        BeanUtils.copyProperties(placeResource, entity);
        return entity;
    }

    public Iterable<Place> toIterable(List<PlaceResource> placeResource) {
        List<Place> places = new ArrayList<>();
        for(PlaceResource p : placeResource) {
            Place temp = new Place();
            temp.setPlace(p.getPlace());
            temp.setDate(p.getDate());
            temp.setRainfall(p.getRainfall());

            places.add(temp);
        }
        return places;
    }
}
