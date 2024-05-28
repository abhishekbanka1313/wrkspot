package com.company.firstApplication.firstApplicaton.service;

import com.company.firstApplication.firstApplicaton.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User addUser(User user);
    List<User> findUserByPageable(int numberOfElements, int pageNumber);
}
