package hr.fer.oop.server.resource;

import java.util.Objects;

public class PlaceResource {
    private String place;
    private String date;
    private Double rainfall;

    public PlaceResource() {
    }

    public PlaceResource(String place, String date, Double rainfall) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceResource resource = (PlaceResource) o;
        return Objects.equals(place, resource.place) &&
                Objects.equals(date, resource.date) &&
                Objects.equals(rainfall, resource.rainfall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, date, rainfall);
    }
}
