package com.mpesa.mpesaservice.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Mpesa_service")
@Data
public class Mpesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String username;
    @Column(nullable = false)
    String phone;
    @Column(nullable = false)
    String merchantId;
    @Column(nullable = false)
    String checkoutId;
    @Column(nullable = false)
    double amount;

    String transactionCode;
    @CreationTimestamp
    Timestamp created_at;

    int status;



}
