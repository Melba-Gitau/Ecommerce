package com.cust.Customer.Repo;

import com.cust.Customer.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustRepo extends JpaRepository<Customer, Long>{

}
