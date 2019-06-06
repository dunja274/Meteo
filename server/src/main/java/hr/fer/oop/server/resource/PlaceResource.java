package hr.fer.oop.server.resource;

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

    private void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public Double getRainfall() {
        return rainfall;
    }

    private void setRainfall(Double ranifall) {
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
}
