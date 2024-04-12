package com.example.EventHub.Event;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {
    @Query("SELECT e FROM Event e " +
            "WHERE (:place IS NULL OR e.place LIKE %:place%) " +
            "AND (:type IS NULL OR e.eventType = :type) " +
            "AND (:date IS NULL OR CAST(e.date AS string) LIKE %:date%) " +
            "AND (:minPrice IS NULL OR :maxPrice IS NULL OR e.ticketPrice BETWEEN :minPrice AND :maxPrice)")
    List<Event> findByPlaceTypeDateAndPrice(@Param("place") String place,
                                    @Param("type") Integer type,
                                    @Param("date") String date,
                                    @Param("minPrice") Double minPrice,
                                    @Param("maxPrice") Double maxPrice);

    @Query("SELECT e FROM Event e " +
            "WHERE (:place IS NULL OR e.place LIKE %:place%)")
    List<Event> findByPlace(@Param("place") String place);

    @Query("SELECT e FROM Event e " +
            "WHERE (:date IS NULL OR CAST(e.date AS string) LIKE %:date%)")
    List<Event> findByDate(@Param("date") String date);

    @Query("SELECT e FROM Event e " +
            "WHERE (:type IS NULL OR e.eventType.typeName LIKE %:type%)")
    List<Event> findByType(@Param("type") String type);
    @Query("SELECT e FROM Event e " +
            "WHERE (:minPrice IS NULL OR :maxPrice IS NULL OR e.ticketPrice BETWEEN :minPrice AND :maxPrice)")
    List<Event> findByPriceRange(@Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice);




}
