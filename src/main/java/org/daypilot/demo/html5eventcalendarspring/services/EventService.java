package org.daypilot.demo.html5eventcalendarspring.services;

import org.daypilot.demo.html5eventcalendarspring.entities.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    List<Event> getEventsBetween(LocalDateTime start, LocalDateTime end);

    Event createEvent(Event event);

    Event moveEvent(Long eventId, LocalDateTime start, LocalDateTime end);

    Event setColor(Long eventId, String color);

    void deleteEvent(Long eventId);
}
