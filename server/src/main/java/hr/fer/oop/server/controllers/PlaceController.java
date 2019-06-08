package hr.fer.oop.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import hr.fer.oop.server.entities.Place;
import hr.fer.oop.server.resource.PlaceResource;
import hr.fer.oop.server.services.GrabberService;
import hr.fer.oop.server.services.PlaceService;

import static hr.fer.oop.server.resource.RepresentationUtility.throwExceptionIfNull;
import static hr.fer.oop.server.resource.RepresentationUtility.throwExceptionIfSetNull;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceAssembler placeAssembler;
    private final GrabberService grabberService;

    @Autowired
    public PlaceController(PlaceService ps,
                           PlaceAssembler placeAssembler,
                           GrabberService grabberService) {
        this.placeService = ps;
        this.placeAssembler = placeAssembler;
        this.grabberService = grabberService;
    }

    @GetMapping(value = "/api/place/{id}")
    public PlaceResource getPlaceById(@PathVariable(value = "id") Integer id) {
        Place place = throwExceptionIfNull(placeService.getWithId(id));
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

    @PostMapping(path = "/api/place/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newPlace(@RequestBody PlaceResource placeResource) {
        boolean exists = placeService.exists(placeResource);

        if(exists == true) {
            return new ResponseEntity("Already exists", HttpStatus.CONFLICT);
        }

        Place place = placeService.save(placeAssembler.toEntity(placeResource));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION,
                linkTo(methodOn(PlaceController.class)
                        .getPlaceById(place.getId())).toString());
        ResponseEntity response = new ResponseEntity(headers, HttpStatus.CREATED);
        return response;
    }

    @PostMapping(path = "/api/places/{date}")
    public ResponseEntity newPlaces(@PathVariable(value = "date") String date) {
        List<PlaceResource> grabberList = new ArrayList<>();
        List<PlaceResource> placesList = new ArrayList<>();

        try {
            grabberList = new ArrayList<>(grabberService.grabPlaces(date));
        } catch (IOException e) {
            e.printStackTrace();
            //return new ResponseEntity("Error connecting to MeteoInfo", HttpStatus.CONFLICT);
        }
        System.out.println(grabberList );
        for(PlaceResource p : grabberList ) {
            boolean exists = placeService.exists(p);
            if(exists == false) {
                placesList.add(p);
            }
        }

        Iterable<Place> places = placeService.saveAll(placeAssembler.toIterable(placesList));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION,
                linkTo(methodOn(PlaceController.class)
                        .getPlaceById(1)).toString());
        ResponseEntity response = new ResponseEntity(headers, HttpStatus.CREATED);
        return response;
    }

    @PostMapping(path = "/api/places/{date1}/{date2}")
    public ResponseEntity newPlaces(@PathVariable(value = "date1") String date1,
                                    @PathVariable(value = "date2") String date2) {
        List<PlaceResource> grabberList = new ArrayList<>();
        List<PlaceResource> placesList = new ArrayList<>();

        try {
            grabberList = new ArrayList<>(grabberService.grabPlaces(date1,date2));
        } catch (IOException e) {
            e.printStackTrace();
            //return new ResponseEntity("Error connecting to MeteoInfo", HttpStatus.CONFLICT);
        }
        System.out.println(grabberList );
        for(PlaceResource p : grabberList ) {
            boolean exists = placeService.exists(p);
            if(exists == false) {
                placesList.add(p);
            }
        }

        Iterable<Place> places = placeService.saveAll(placeAssembler.toIterable(placesList));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION,
                linkTo(methodOn(PlaceController.class)
                        .getPlaceById(1)).toString());
        ResponseEntity response = new ResponseEntity(headers, HttpStatus.CREATED);
        return response;
    }

    @PutMapping(value = "/api/place/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePlace(@PathVariable("id") Integer id,
                                      @RequestBody PlaceResource placeResource) {
        try {
            Place place = placeAssembler.toEntity(placeResource);
            place.setId(id);
            placeService.update(place);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .eTag(e.getMessage())
                    .build();
        }
    }

    @DeleteMapping("/api/place/{id}")
    public ResponseEntity deletePlace(@PathVariable("id") Integer id) {
        placeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
