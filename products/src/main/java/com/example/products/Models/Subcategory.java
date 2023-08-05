package com.example.products.Models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name = "Subcategory")
@Data
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String category_id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String description;

    @CreationTimestamp
    Timestamp created_at;

    @UpdateTimestamp
    Timestamp updated_at;
}
