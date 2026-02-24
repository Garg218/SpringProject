package com.example.ecomProject.repository;

import com.example.ecomProject.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
    Page<Product> findByCategory(String category, Pageable pageable);
}
