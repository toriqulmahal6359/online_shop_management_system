package com.mahal.major.service;

import com.mahal.major.model.Category;
import com.mahal.major.model.Product;
import com.mahal.major.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() { return productRepository.findAll(); }
    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }
    public Optional<Product> getAllProductsById(long id) {
        return productRepository.findById(id);
    }
    public List<Product> getAllProductsByCategoryId(int id) {
        return productRepository.findAllByCategoryId(id);
    }
}
