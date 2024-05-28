package com.company.firstApplication.firstApplicaton.security;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByClientName(String clientName);

    Page<Client> findAll(Pageable pageable);
}
