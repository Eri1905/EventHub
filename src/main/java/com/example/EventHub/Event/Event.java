package com.example.EventHub.Event;

import com.example.EventHub.EventType.EventType;
import com.example.EventHub.Organisation.Organisation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.processing.SQL;

import java.util.Date;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "The name of the event can not be empty!")
    private String name;
    @NotEmpty(message = "Fill in the date of the event!")
    private Date date;
    @NotNull(message = "Please enter the duration of the event!")
    private int duration;
    @NotEmpty(message = "Please enter description!")
    private String description;
    @NotEmpty(message = "Please enter the place of the event!")

    private String place;
    @NotNull(message = "Please enter the price of the ticket for the event!")
    private int ticketPrice;
    @NotNull(message = "Please enter the capacity of the event!")
    private int capacity;
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    @NotEmpty
    private EventType eventType;
    @NotEmpty
    private EventStatus eventStatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
}
