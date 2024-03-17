package com.example.EventHub.Event;

import com.example.EventHub.EventType.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

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
            model.addAttribute("event", event);
            return "event-update-form";
        } else {
            return "id could not be find";
        }
    }
    public String postUpdate(Integer id, Event updatedEvent, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("allEventTypes", eventTypeRepository.findAll());
            return "event-update-form";
        } else {
            Event event = eventRepository.findById(id).get();
            event.setName(updatedEvent.getName());
            event.setEventType(updatedEvent.getEventType());
            event.setEventStatus(updatedEvent.getEventStatus());
            //to-do drugite poleta

            eventRepository.save(event);
            model.addAttribute("event", event);
            return "event-update-result";
        }
    }

    public String delete(Integer id, Model model) {
        Event event = eventRepository.findById(id).get();
        eventRepository.delete(event);
        model.addAttribute("event", event);
        return "event-delete";
    }
}
