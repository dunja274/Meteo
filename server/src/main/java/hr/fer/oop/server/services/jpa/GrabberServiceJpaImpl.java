package hr.fer.oop.server.services.jpa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.text.*;
import java.util.List;

import hr.fer.oop.server.resource.PlaceResource;
import hr.fer.oop.server.services.GrabberService;

@Service
public class GrabberServiceJpaImpl implements GrabberService {

   /* public static void main(String[] args) throws IOException{
        GrabberServiceJpaImpl g = new GrabberServiceJpaImpl();
        g.grabPlaces("2019-05-09","2019-05-12");
        //g.grabRainfall();
    }*/

    public GrabberServiceJpaImpl() {
    }

    public List<PlaceResource> grabPlaces(String... date) throws IOException {

        List<String> dates = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = null;
        Date endDate = null;

        try {
            startDate = formatter.parse(date[0]);
            if(date.length > 1) endDate = formatter.parse(date[1]);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);

        dates.add(formatter.format(start.getTime()));
        start.add(Calendar.DATE, 1);

        if(date.length > 1) {
            Calendar end = Calendar.getInstance();
            end.setTime(endDate);
            end.add(Calendar.DATE, 1);
            for (Date d = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), d = start.getTime()) {
                //System.out.println(d);
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = f.format(d);
                // System.out.println(strDate);
                //String satestr = DateFormat.format(d);
                dates.add(strDate);
            }
        }


        List<PlaceResource> placesList = new ArrayList<>();

        for (String d : dates) {
            Document doc = Jsoup.connect("http://www.meteo-info.hr/povijesni-podaci/" + d).get();
            Elements places = doc.select("ul.pseudo-columns li");
            Elements rainfall = doc.select("ul.pseudo-columns li");
            for (int i = 0; i < places.size(); i++) {
                String[] rain = (places.get(i).text()).split(" ");
                placesList.add(new PlaceResource(places.get(i).ownText(), d, Double.parseDouble(rain[0].replace(",", "."))));
            }
        }

        return placesList;
    }
}
