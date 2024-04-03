package com.example.EventHub.Event;

import com.example.EventHub.EventType.EventType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {
    @Query("SELECT e FROM Event e WHERE e.place LIKE %?1% AND e.eventType.typeName LIKE %?2% AND CAST(e.date AS string) LIKE %?3%")
    List<Event> findByPlaceTypeDate(String place, String type, String date);
    @Query("SELECT e FROM Event e WHERE e.place LIKE %?1%")
    List<Event> findByPlace(String place);
    @Query("SELECT e FROM Event e WHERE CAST(e.date AS string) LIKE %?1%")
    List<Event> findByDate(String date);
    @Query("SELECT e FROM Event e WHERE e.eventType.typeName LIKE %?1%")
    List<Event> findByType(String eventType);

}
