package com.example.products.Models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "products_catalogue")
@Data
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String subcategory_id;
    @Column (nullable= false)

    String product_name;

    @Column (nullable= false)
    String description;
    @Column(nullable = false)
    String image;

    @Column(nullable = false)
    double price;

    @CreationTimestamp
    Timestamp created_at;
    @UpdateTimestamp
    Timestamp updated_at;
}
