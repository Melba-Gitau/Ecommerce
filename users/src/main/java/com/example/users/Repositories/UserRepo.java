package com.example.users.Repositories;

import com.example.users.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {

    Optional<Users> findById(Long aLong);
    Optional<Users> findByPhoneAndStatus(String phone,String status);
}
