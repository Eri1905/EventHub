package com.example.EventHub.Event;

import com.example.EventHub.EventType.EventTypeRepository;
import com.example.EventHub.Organisation.OrganisationRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventTypeRepository eventTypeRepository;
    @Autowired
    OrganisationRepository organisationRepository;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate localDate = LocalDate.now();

    public String updateForm(Integer id, Model model) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = eventRepository.findById(id).get();
            model.addAttribute("eventTypes", eventTypeRepository.findAll());
            model.addAttribute("organisations", organisationRepository.findAll());
            model.addAttribute("updateEvent", event);
            return "event-update-form";
        } else {
            return "id could not be find";
        }
    }

    public String postUpdate(Integer id, Event updatedEvent, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("eventTypes", eventTypeRepository.findAll());
            model.addAttribute("organisations", organisationRepository.findAll());
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

    public String searchEvents(String place,
                               Integer type,
                               String date,
                               Double minPrice,
                               Double maxPrice,
                               Model model) {

        if (place == null) {
            place = ""; // Set default value or handle as needed
        }
        if (date == null) {
            date = "";
        }

        if (minPrice == null) {
            minPrice = (double) 0; // Set default value or handle as needed
        }
        if (maxPrice == null) {
            maxPrice = (double) Integer.MAX_VALUE; // Set default value or handle as needed
        }
        if (minPrice > maxPrice) {
            double maxPrice1 = maxPrice;
            maxPrice = minPrice;
            minPrice = maxPrice1;
        }

        model.addAttribute("eventTypes", eventRepository.findByPlaceTypeDateAndPrice(place, type, date, minPrice, maxPrice));
        model.addAttribute("allTypes", eventTypeRepository.findAll());
        return "all-events";
    }

    public List<Event> searchEventsByPlace(String place) {
        if (place != null) {
            return eventRepository.findByPlace(place);
        } else {
            return (List<Event>) eventRepository.findAll();
        }
    }

    public List<Event> searchEventsByType(String type) {
        if (type != null) {
            return eventRepository.findByType(type);
        } else {
            return (List<Event>) eventRepository.findAll();
        }
    }

    public List<Event> searchEventsByDate(String date) {
        if (date != null) {
            return eventRepository.findByDate(date);
        } else {
            return (List<Event>) eventRepository.findAll();
        }
    }

    public List<Event> searchEventsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice != null || maxPrice != null) {
            return eventRepository.findByPriceRange(minPrice, maxPrice);
        } else {
            return (List<Event>) eventRepository.findAll();
        }
    }

    public boolean errorEventStatus(EventDTO eventDTO) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(eventDTO.getDate());
            Date local = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (date.before(local)) {
                return true;
            }
        } catch (ParseException ex) {
            System.out.println("Parsing error!" + ex);
        }
        return false;
    }
}