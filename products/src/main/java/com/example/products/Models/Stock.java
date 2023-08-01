package com.example.products.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Product_stock")
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @Column(nullable= false)
    double price;
    @Column(nullable = false)
    Long productid;
    @Column (nullable= false)
    double quantity;



}
