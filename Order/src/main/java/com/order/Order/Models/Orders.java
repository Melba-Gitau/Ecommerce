package com.order.Order.Models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    String CustomerDetails;

    @Column(nullable = false)
    String ProductDetails;
    @Column(nullable = false)
    String DeliveryDetails;
    @Column(nullable = false)
    String BilingDetails;
    @CreationTimestamp
    Timestamp created_at;
    @UpdateTimestamp
    Timestamp updated_at;

}
