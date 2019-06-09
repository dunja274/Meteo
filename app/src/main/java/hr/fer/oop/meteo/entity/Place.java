package hr.fer.oop.meteo.entity;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Place implements Serializable {

    private Integer id;
    private String place;
    private String date;
    private Double rainfall;

    public Place() {
    }

    public Place(Integer id, String place, String date, Double rainfall) {
        setId(id);
        setPlace(place);
        setDate(date);
        setRainfall(rainfall);
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getRainfall() {
        return rainfall;
    }

    public void setRainfall(Double ranifall) {
        this.rainfall = ranifall;
    }

    @Override
    public String toString() {
        return "PlaceResource{" +
                "place='" + place + '\'' +
                ", date=" + date +
                ", ranifall=" + rainfall +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


