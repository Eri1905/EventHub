package com.example.EventHub.Event;

import com.example.EventHub.EventType.EventTypeRepository;
import com.example.EventHub.Organisation.OrganisationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/add")
    public String addEvent(Model model){
            model.addAttribute("event", new Event());
            model.addAttribute("eventTypes", eventTypeRepository.findAll());
            model.addAttribute("organisations", organisationRepository.findAll());
            return "event-form";
    }
    @PostMapping("/submit")
    public String postProduct(@Valid @ModelAttribute Event event, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allEventTypes", eventTypeRepository.findAll());
            return "event-form";
        } else {
            eventRepository.save(event);
            model.addAttribute("event", event);
            return "event-result";
        }
    }
    @GetMapping("/all")
    public String allEvents(Model model){
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
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id, Model model) {
        return eventService.delete(id, model);
    }

    }

