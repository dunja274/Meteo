package hr.fer.oop.server.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO(Dino) : Maybe rename this to Entity

@Entity
@Table(name = "meteo")
public class Place {
    @Id
    private Integer id;

    private String date;
    private String place;
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

    private void setPlace(String place) {
        this.place = place;
    }

    public Double getRainfall() {
        return rainfall;
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    private void setRainfall(Double rainfall) {
        this.rainfall = rainfall;
    }

}
