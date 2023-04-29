package com.example.products.ProductRepo;

import com.example.products.Models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<Stock, Long > {
}
