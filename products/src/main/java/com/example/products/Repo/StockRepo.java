package com.example.products.Repo;

import com.example.products.Models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepo extends JpaRepository<Stock, Long > {
//    List<Stock> findByProduct_id(Long id);


//    List<Stock> findAllByProduct_id(Long Id);


    List<Stock> findAllByProductid(Long id);

}
