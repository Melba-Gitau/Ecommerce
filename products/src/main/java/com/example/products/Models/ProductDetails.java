package com.example.products.Models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Products-Catalogue")
@Data
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (nullable= false)
    String name;
    @Column (nullable= false)
    double price;
    @Column (nullable= false)
    double quantity;
    @CreationTimestamp
    Timestamp created_at;
    @UpdateTimestamp
    Timestamp updated_at;
}
