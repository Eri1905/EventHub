package com.example.EventHub.Event;

import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public Event toEntity(EventDTO eventDTO){
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());
        event.setDuration(eventDTO.getDuration());
        event.setDescription(eventDTO.getDescription());
        event.setPlace(eventDTO.getPlace());
        event.setTime(eventDTO.getTime());
        event.setTicketPrice(eventDTO.getTicketPrice());
        event.setCapacity(eventDTO.getCapacity());
        event.setOrganisation(eventDTO.getOrganisation());
        event.setEventType(eventDTO.getEventType());
        event.setEventStatus(eventDTO.getEventStatus());
        return event;
    }
}
