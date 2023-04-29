package com.example.products.Models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product_stock")
@Data
public class Stock {
    @Id
    Long Id;
    @Column(nullable= false)
    double price;
    @Column (nullable= false)
    double quantity;


}
