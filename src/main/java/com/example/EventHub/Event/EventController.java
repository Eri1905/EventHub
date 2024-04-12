package com.example.EventHub.Event;


import com.example.EventHub.EventType.EventTypeRepository;
import com.example.EventHub.Organisation.OrganisationRepository;
import com.example.EventHub.User.User;
import com.example.EventHub.User.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.util.List;
import java.util.Optional;


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
    EventMapper eventMapper;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/add")
    public String addEvent(Model model) {
        model.addAttribute("eventDTO", new EventDTO());
        model.addAttribute("eventTypes", eventTypeRepository.findAll());
        model.addAttribute("organisations", organisationRepository.findAll());
        return "event-form";
    }

    @PostMapping("/submit")
    public String postEvent(@Valid @ModelAttribute EventDTO eventDTO, BindingResult bindingResult, Model model) throws ParseException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("eventTypes", eventTypeRepository.findAll());
            model.addAttribute("organisations", organisationRepository.findAll());
            return "event-form";
        }
        if (eventService.errorEventStatus(eventDTO)) {
            model.addAttribute("eventTypes", eventTypeRepository.findAll());
            model.addAttribute("organisations", organisationRepository.findAll());
            model.addAttribute("notValidDate", "Please enter a valid date!");
            return "event-form";
        } else {
            Event event = eventMapper.toEntity(eventDTO);
            eventRepository.save(event);
            model.addAttribute("event", event);
            return "home";
        }
    }

    @GetMapping("/all")
    public String allEvents(Model model) throws ParseException {
        Iterable<Event> allEvents = eventRepository.findAll();
        model.addAttribute("allEvents", allEvents);
        model.addAttribute("allTypes", eventTypeRepository.findAll());
        return "all-events";
    }

    @GetMapping("/{eventId}")
    public String getEventDetails(@PathVariable Integer eventId, Model model) throws ParseException {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            model.addAttribute("event", event);
            return "event-details";
        } else {
            return "id could not be find";
        }
    }

    @GetMapping("/search")
    public String searchEvents(@RequestParam(name = "place") String place,
                               @RequestParam(name = "type") Integer type,
                               @RequestParam(name = "date") String date,
                               @RequestParam(name = "minPrice") Double minPrice,
                               @RequestParam(name = "maxPrice") Double maxPrice,
                               Model model) {
        return eventService.searchEvents(place, type, date, minPrice, maxPrice, model);
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


    @PostMapping("/apply")
    public String apply(@RequestParam(name = "eventId") Integer id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.getUserByUsername(username);

        Event event = eventRepository.findById(id).get();
        List<Event> events = user.getEvents();
        for (Event listEvent : events) {
            if (listEvent.equals(event)) {
                model.addAttribute("event", event);
                return "event-details";
            }
        }
        event.getUsers().add(user);
        user.getEvents().add(event);
        userRepository.save(user);
        eventRepository.save(event);

        model.addAttribute("event", event);
        return "event-details";
    }
}

