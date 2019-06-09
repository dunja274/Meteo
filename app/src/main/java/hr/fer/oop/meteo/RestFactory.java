package hr.fer.oop.meteo;

import hr.fer.oop.meteo.util.PlacesRetrofit;
import hr.fer.oop.meteo.util.PlacesRetrofitService;

public class RestFactory {

    public static final String BASE_IP = "10.0.2.2";

    public static RestInterface getInstance() {
        //return new InMemoryRestImplementation();

        // HTTP

        //return new RestSpringTemplete();
        return new PlacesRetrofit();

        // HTTPS
        //return new HttpsRestUrlConnection("user", "user");
        //return new HttpsRestSpringTemplete("user", "user");
        //return new HttpsRestRetrofit("user", "user");


        //return new RestUrlConnectionXML();
    }
}
