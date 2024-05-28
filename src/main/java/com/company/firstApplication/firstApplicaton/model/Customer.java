package com.company.firstApplication.firstApplicaton.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String customerId;

    @Min(value = 1, message = "Age must be greater than 0")
    private int age;

    @Min(value = 0, message = "Spending limit must be greater than or equal to zero")
    private BigDecimal spendingLimit;

    @NotBlank
    @Size(max = 15, message = "Mobile number must not exceed 15 characters")
    private String phoneNumber;

    @Valid
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Address> address = new HashSet<>();
}
