package com.company.firstApplication.firstApplicaton.security;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENT_ID")
    private long id;

    @Column(name = "CLIENT_NAME", nullable = false, unique = true)
    private String clientName;

    @Column(name = "PASSWORD")
    private String password;
}
