package org.daypilot.demo.html5eventcalendarspring.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String fullName;
    private String email;
    private String address;
    private String hashedPassword;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    public User(String fullName, String email, String address, String hashedPassword) {
        this.fullName = fullName;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public void addEvent(Event event) {
        events.add(event);
        event.setUser(this);
    }

    public void removeEvent(Event event) {
        events.remove(event);
        event.setUser(null);
    }
}
