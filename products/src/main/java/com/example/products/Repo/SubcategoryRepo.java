package com.example.products.Repo;

import com.example.products.Models.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {
}
