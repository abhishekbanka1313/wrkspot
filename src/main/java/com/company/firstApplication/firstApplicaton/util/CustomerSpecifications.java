package com.company.firstApplication.firstApplicaton.util;

import com.company.firstApplication.firstApplicaton.model.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class CustomerSpecifications {
    public static Specification<Customer> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(firstName) ? criteriaBuilder.equal(root.get("firstName"), firstName) : null;
    }

    public static Specification<Customer> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(lastName) ? criteriaBuilder.equal(root.get("lastName"), lastName) : null;
    }

    public static Specification<Customer> hasState(String state) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(state)) {
                return criteriaBuilder.equal(root.join("address").get("state"), state);
            } else {
                return null;
            }
        };
    }
}
