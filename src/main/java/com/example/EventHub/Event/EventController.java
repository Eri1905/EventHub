package com.example.EventHub.Event;


import com.example.EventHub.EventStatus.EventStatusRepository;
import com.example.EventHub.EventType.EventTypeRepository;
import com.example.EventHub.Organisation.OrganisationRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventTypeRepository eventTypeRepository;
    @Autowired
    OrganisationRepository organisationRepository;
    @Autowired
    EventService eventService;
    @Autowired
    EventStatusRepository eventStatusRepository;
    @Autowired
    EventMapper eventMapper;

    @GetMapping("/add")
    public String addEvent(Model model) {
        model.addAttribute("eventDTO", new EventDTO());
        model.addAttribute("eventTypes", eventTypeRepository.findAll());
        model.addAttribute("organisations", organisationRepository.findAll());
        model.addAttribute("allStatuses", eventStatusRepository.findAll());
        return "event-form";
    }

    @PostMapping("/submit")
    public String postProduct(@Valid @ModelAttribute EventDTO eventDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("eventTypes", eventTypeRepository.findAll());
            model.addAttribute("organisations", organisationRepository.findAll());
            model.addAttribute("allStatuses", eventStatusRepository.findAll());
            return "event-form";
        } else {
            Event event = eventMapper.toEntity(eventDTO);
            eventRepository.save(event);
            model.addAttribute("event", event);
            return "home";
        }
    }

    @GetMapping("/all")
    public String allEvents(Model model) {
        Iterable<Event> allEvents = eventRepository.findAll();
        model.addAttribute("allEvents", allEvents);
        return "all-events";
    }

    @GetMapping("/update")
    public String updateProductForm(@RequestParam("id") Integer id, Model model) {
        return eventService.updateForm(id, model);
    }

    @PostMapping("/update")
    public String postUpdatedProduct(@RequestParam("id") Integer id, @Valid @ModelAttribute Event updatedEvent, BindingResult bindingResult, Model model) {
        return eventService.postUpdate(id, updatedEvent, bindingResult, model);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id, Model model) {
        return eventService.delete(id, model);
    }

    @PostMapping("/filter")
    public String getEventsBySearchCriteria(
            @RequestParam(required = false) String place,
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) String date,
            Model model) {
        List<Event> eventsFilter = eventService.findEventsBySearchCriteria(place, eventType, date);
        model.addAttribute("eventsFilter", eventsFilter);
        return "events-filter";
    }
}

