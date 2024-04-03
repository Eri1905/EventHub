package com.example.EventHub.Event;

import com.example.EventHub.EventType.EventType;
import com.example.EventHub.EventType.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventTypeRepository eventTypeRepository;

    public String updateForm(Integer id, Model model) {

        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = eventRepository.findById(id).get();
            model.addAttribute("allEventTypes", eventTypeRepository.findAll());
            model.addAttribute("updateEvent", event);
            return "event-update-form";
        } else {
            return "id could not be find";
        }
    }

    public String postUpdate(Integer id, Event updatedEvent, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("allEventTypes", eventTypeRepository.findAll());
            return "event-update-form";
        } else {
            Event event = eventRepository.findById(id).get();
            getEvent(event, updatedEvent);
            eventRepository.save(event);
            model.addAttribute("event", event);
            return "event-update-result";
        }
    }

    private Event getEvent(Event event, Event updatedEvent) {
        event.setName(updatedEvent.getName());
        event.setDate(updatedEvent.getDate());
        event.setDuration(updatedEvent.getDuration());
        event.setDescription(updatedEvent.getDescription());
        event.setPlace(updatedEvent.getPlace());
        event.setTicketPrice(updatedEvent.getTicketPrice());
        event.setCapacity(updatedEvent.getCapacity());
        event.setOrganisation(updatedEvent.getOrganisation());
        event.setEventType(updatedEvent.getEventType());
        event.setEventStatus(updatedEvent.getEventStatus());
        return event;
    }

    public String delete(Integer id, Model model) {
        Event event = eventRepository.findById(id).get();
        eventRepository.delete(event);
        model.addAttribute("event", event);
        return "event-delete";
    }
    public List<Event> findEventsBySearchCriteria(String place, String eventType, String date) {
        if (place != null && !place.isEmpty() && eventType != null && !eventType.isEmpty() && date != null && !date.isEmpty()) {
            return eventRepository.findByPlaceTypeDate(place, eventType, date);
        } else if (place != null && !place.isEmpty()) {
            return eventRepository.findByPlace("%" + place + "%");
        } else if (eventType != null && !eventType.isEmpty()) {
            return eventRepository.findByType("%" + eventType + "%");
        } else if (date != null && !date.isEmpty()) {
            return eventRepository.findByDate("%" + date + "%");
        } else {
            return (List<Event>) eventRepository.findAll();
        }
    }
}