package hr.fer.oop.server.respositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import hr.fer.oop.server.entities.Place;

public interface WeatherRepository extends CrudRepository<Place, Integer> {

    @Query("select p from Place p where p.place = :place and p.date = :date")
    List<Place> getWeatherByDate(@Param(value = "place") String place,
                                 @Param(value = "date") String date);

    @Query("select p from Place p where p.place =:place and p.date between :date1 and :date2")
    List<Place> getWeatherByDates(@Param(value="place") String place,
                                  @Param(value="date1") String date1,
                                  @Param(value="date2") String date2);
}
