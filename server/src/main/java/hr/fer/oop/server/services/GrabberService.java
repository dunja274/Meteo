package hr.fer.oop.server.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import hr.fer.oop.server.resource.PlaceResource;

public interface GrabberService {

    List<PlaceResource> grabPlaces(String ... date) throws IOException;


}
