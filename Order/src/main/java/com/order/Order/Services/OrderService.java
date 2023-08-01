package com.order.Order.Services;

import com.order.Order.Models.Orders;
import com.order.Order.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;

    public boolean create(Orders orders){
        try{
            orderRepo.save(orders);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public List<Orders> listOrders(){
        return orderRepo.findAll();
    }

    public boolean updateOrder(Long Id, Orders orders){
        Optional<Orders> oldOrder = orderRepo.findById(Id);
        if (oldOrder.isPresent()) {
            try {
                Orders updated = oldOrder.get();
                updated.setCustomerDetails(orders.getCustomerDetails());
                updated.setProductDetails(orders.getProductDetails());
                updated.setDeliveryDetails(orders.getDeliveryDetails());
                updated.setBilingDetails(orders.getBilingDetails());
                orderRepo.save(orders);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean deleteOrder(Long Id){
        Optional <Orders> oldOrder = orderRepo.findById(Id);
        if(oldOrder.isEmpty()){
            return false;
        }else{
            orderRepo.delete(oldOrder.get());
            return true;
        }
    }
}
