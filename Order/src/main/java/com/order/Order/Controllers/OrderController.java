package com.order.Order.Controllers;

import com.order.Order.Models.Orders;
import com.order.Order.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public HashMap<String, Object> map(@RequestBody Orders orders){
        HashMap<String, Object> map = new HashMap<>();
        boolean create = orderService.create(orders);
        if(create){
            map.put("Success",true);
            map.put("Message","Orders added successfully");
        }else{
            map.put("Success",false);
            map.put("Message","Failed to add Orders");
        }
        return map;
    }

    @GetMapping("/list")
    public HashMap<String, Object> map(){
        HashMap<String, Object> map = new HashMap<>();
        List<Orders> orders = orderService.listOrders();
        if (orders.isEmpty()) {
            map.put("Success", true);
            map.put("Message", "orders not found");
        } else {
            map.put("Success", false);
            map.put("data", orders);
        }
        return map;
    }

    @PutMapping("/update/{id}")
    public HashMap<String, Object> updateList(@RequestBody Orders orders, @PathVariable Long id){
        HashMap<String, Object> map = new HashMap<>();
        boolean update = orderService.updateOrder(id,orders);
        if(update){
            map.put("Success", true);
            map.put("Message", "Orders updated");
        } else {
            map.put("Success", false);
            map.put("data", "Failed to update");
        }
        return map;
    }

    @DeleteMapping("/delete/{id}")
    public  HashMap<String, Object> delete(Long id){
        HashMap<String,Object> map = new HashMap<>();
        boolean del = orderService.deleteOrder(id);
        if(del){
            map.put("Success", true);
            map.put("Message", "order deleted");
        } else {
            map.put("Success", false);
            map.put("Message", "order not found");
        }
        return map;
    }
}
