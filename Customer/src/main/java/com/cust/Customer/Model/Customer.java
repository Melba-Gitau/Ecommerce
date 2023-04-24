package com.cust.Customer.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table (name = "Customers")
@Data
public class Customer {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String contacts;

    @Column(nullable = false)
    String location;

    @CreationTimestamp
    Timestamp created_at;

    @UpdateTimestamp
    Timestamp updated_at;


}
