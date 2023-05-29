package org.daypilot.demo.html5eventcalendarspring.services;

import org.daypilot.demo.html5eventcalendarspring.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(String userId);

    User createUser(User user);

    User updateUser(String userId, User updatedUser);

    void deleteUser(String userId);
}
