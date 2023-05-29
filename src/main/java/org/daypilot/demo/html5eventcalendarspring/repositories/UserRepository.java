package org.daypilot.demo.html5eventcalendarspring.repositories;

import org.daypilot.demo.html5eventcalendarspring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
