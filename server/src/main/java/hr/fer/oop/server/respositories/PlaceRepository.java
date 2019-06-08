package hr.fer.oop.server.respositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

import hr.fer.oop.server.entities.Place;

public interface PlaceRepository extends CrudRepository<Place, Integer> {

    @Query("select p from Place p where p.date = :date")
    Set<Place> getByDate(@Param(value = "date") String date);

    @Query("select p from Place p where p.date between :date1 AND :date2")
    Set<Place> getByDates(@Param(value = "date1") String date1,
                          @Param(value = "date2") String date2);

    @Query("select case when count(p)> 0 then true else false end from Place p where p.date =:date"
            + " AND p.place =:place AND p.rainfall =:rainfall")
    boolean exists(@Param(value = "date") String date,
                   @Param(value = "place") String place,
                   @Param(value = "rainfall") Double rainfall);
}
