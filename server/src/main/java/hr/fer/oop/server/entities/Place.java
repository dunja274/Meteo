package hr.fer.oop.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meteo")
public class Place {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String place;
    private String date;
    private Double rainfall;

    public Place() {
    }

    public Place(String place, String date, Double rainfall) {
        setPlace(place);
        setDate(date);
        setRainfall(rainfall);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Double getRainfall() {
        return rainfall;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRainfall(Double rainfall) {
        this.rainfall = rainfall;
    }

}
