package hr.fer.oop.server.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
}
