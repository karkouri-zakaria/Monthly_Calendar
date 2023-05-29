package org.daypilot.demo.html5eventcalendarspring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String text;

    @Column(name = "event_start")
    LocalDateTime start;

    @Column(name = "event_end")
    LocalDateTime end;

    String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
