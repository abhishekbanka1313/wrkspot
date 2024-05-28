package com.company.firstApplication.firstApplicaton.service;

import com.company.firstApplication.firstApplicaton.dao.UserDAO;
import com.company.firstApplication.firstApplicaton.exception.UserNotFoundException;
import com.company.firstApplication.firstApplicaton.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserDAO userDAO;

    @Override
    public List<User> getAllUsers() {
        logger.info("In UserServiceImpl::getAllUsers");
        List<User> users =  userDAO.findAll();
        logger.info("Number of users:{}:done", users.size());
        logger.info("users:{}", users);
        return users;
    }

    @Override
    public User getUserById(Long id) {
        logger.info("In UserServiceImpl::getUserById");
        return userDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: "+id+" is not found."));
    }

    @Override
    public User addUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public List<User> findUserByPageable(int numberOfElements, int pageNumber) {
        Page<User> users = userDAO.findAll(PageRequest.of(pageNumber, numberOfElements, Sort.by("firstName").and(Sort.by("lastName").descending())));
        users.getNumber();
        return null;
    }
}
