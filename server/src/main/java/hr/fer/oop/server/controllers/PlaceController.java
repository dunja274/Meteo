package hr.fer.oop.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import hr.fer.oop.server.entities.Place;
import hr.fer.oop.server.resource.PlaceResource;
import hr.fer.oop.server.services.PlaceService;

import static hr.fer.oop.server.resource.RepresentationUtility.throwExceptionIfNull;
import static hr.fer.oop.server.resource.RepresentationUtility.throwExceptionIfSetNull;

//TODO(Dino) : Add set functions (Place)

@RestController
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceAssembler placeAssembler;

    @Autowired
    public PlaceController(PlaceService ps,
                           PlaceAssembler placeAssembler) {
        this.placeService = ps;
        this.placeAssembler = placeAssembler;
    }

    @GetMapping(value = "/api/place/{id}")
    public PlaceResource getPlaceById(@PathVariable(value = "id") String id) {
        Place place = throwExceptionIfNull(placeService.getWithId(Integer.parseInt(id)));
        return placeAssembler.toResource(place);
    }

    @GetMapping(value = "/api/date/{date}")
    public Set<String> getPlacesByDate(@PathVariable(value = "date") String date) {
        Set<Place> places = throwExceptionIfSetNull(placeService.getByDate(date));
        return placeAssembler.toResourceSet(places);
    }

    @GetMapping(value = "/api/dates/{date1}/{date2}")
    public Set<String> getPlacesByDates(@PathVariable(value = "date1") String date1,
                                        @PathVariable(value = "date2") String date2) {
        Set<Place> places = throwExceptionIfSetNull(placeService.getByDates(date1, date2));
        return placeAssembler.toResourceSet(places);
    }
}
