package com.example.products.Repo;

import com.example.products.Models.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductDetails, Long> {
}
