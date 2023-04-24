package com.example.users.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.MediaType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name ="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String phone;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String status;

    @Column(nullable = false)
    String role;

    @CreationTimestamp
    Timestamp created_at;

    @UpdateTimestamp
    Timestamp updated_at;

}
