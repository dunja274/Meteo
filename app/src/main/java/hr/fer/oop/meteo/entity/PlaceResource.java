package hr.fer.oop.meteo.entity;
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

}


