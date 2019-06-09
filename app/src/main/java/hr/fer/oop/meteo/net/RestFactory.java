package hr.fer.oop.meteo.net;

public class RestFactory {

    public static final String BASE_IP = "10.0.2.2";

    public static RestInterface getInstance() {
       return new PlacesRetrofit();
    }
}
