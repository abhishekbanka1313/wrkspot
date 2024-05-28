package com.company.firstApplication.firstApplicaton.dao;

import com.company.firstApplication.firstApplicaton.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
}
