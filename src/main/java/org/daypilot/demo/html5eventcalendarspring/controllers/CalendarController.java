package org.daypilot.demo.html5eventcalendarspring.controllers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.transaction.Transactional;
import org.daypilot.demo.html5eventcalendarspring.entities.Event;
import org.daypilot.demo.html5eventcalendarspring.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class CalendarController {

    private final EventRepository eventRepository;

    @Autowired
    public CalendarController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public ResponseEntity<List<Event>> getEventsBetween(
            @RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
        List<Event> events = eventRepository.findBetween(start, end);
        return ResponseEntity.ok(events);
    }

    @PostMapping("/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @PutMapping("/move/{id}")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public ResponseEntity<Event> moveEvent(
            @PathVariable("id") Long eventId,
            @RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        event.setStart(start);
        event.setEnd(end);
        Event updatedEvent = eventRepository.save(event);
        return ResponseEntity.ok(updatedEvent);
    }

    @PutMapping("/setColor/{id}")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public ResponseEntity<Event> setColor(
            @PathVariable("id") Long eventId,
            @RequestParam("color") String color) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        event.setColor(color);
        Event updatedEvent = eventRepository.save(event);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Long eventId) {
        eventRepository.deleteById(eventId);
        return ResponseEntity.noContent().build();
    }
}
