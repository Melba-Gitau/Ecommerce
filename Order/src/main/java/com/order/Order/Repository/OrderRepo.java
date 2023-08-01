package com.order.Order.Repository;

import com.order.Order.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepo extends JpaRepository<Orders, Long> {
}
