package com.mahal.major.repository;

import com.mahal.major.model.Category;
import com.mahal.major.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(int id);
}
