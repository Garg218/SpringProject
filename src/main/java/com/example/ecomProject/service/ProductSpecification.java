package com.example.ecomProject.service;

import com.example.ecomProject.model.Product;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> filter(String category,Boolean available ) {

        return (root, query, cb) ->
        {
            List<Predicate> predicates = new ArrayList<>();

            if (category != null) {
                predicates.add(cb.equal(root.get("category"), category));
            }

            if (available != null) {
                predicates.add(cb.equal(root.get("available"), available));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
