package hr.fer.oop.meteo.server;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;


public class Grabber {

   /* public static void main(String[] args) throws IOException{
        Grabber g = new Grabber("2019-05-09");
        g.grabPlaces();
        g.grabRainfall();
    }*/

    private String date;
    public Grabber(String date) {
        this.date=date;
    }

    public Set<String> grabPlaces() throws IOException {
        Set<String> placesSet = new LinkedHashSet<>();
        Document doc = Jsoup.connect("http://www.meteo-info.hr/povijesni-podaci/" + date).get();
        //System.out.println(doc.title());
        Elements places = doc.select("ul.pseudo-columns li");
       for (Element place : places) {
            //System.out.println(place.ownText());
            placesSet.add(place.ownText());

       }
       return placesSet;
    }

    public List<Double> grabRainfall() throws IOException {
        List<Double> rainfallSet = new ArrayList<>();
        Document doc = Jsoup.connect("http://www.meteo-info.hr/povijesni-podaci/" + date).get();
        //System.out.println(doc.title());
        Elements rainfall = doc.select("ul.pseudo-columns li");
        for (Element el : rainfall) {
            String[] rain = (el.text()).split(" ");
            //System.out.println(rain[0]);
            rainfallSet.add(Double.parseDouble(rain[0].replace(",",".")));
        }

        return rainfallSet;
    }
}
