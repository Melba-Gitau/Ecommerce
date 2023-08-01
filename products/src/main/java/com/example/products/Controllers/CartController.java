package com.example.products.Controllers;

import com.example.products.Models.Cart;
import com.example.products.Models.Stock;
import com.example.products.Services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartServices cartServices;

    @PostMapping("/create")
    public HashMap<String, Object> map(@RequestBody Cart cart){
        HashMap<String, Object> map = new HashMap<>();
        boolean create = cartServices.create(cart);
        if(create){
            map.put("Success",true);
            map.put("Message","Details added successfully");
        }else{
            map.put("Success",false);
            map.put("Message","Failed to add Details");
        }
        return map;
    }

    @GetMapping("/list")
    public HashMap<String, Object> map(){
        HashMap<String, Object> map = new HashMap<>();
        List<Map<String,Object>> cart = cartServices.listInCart();
        if (cart.isEmpty()) {
            map.put("Success", true);
            map.put("Message", "item not found");
        } else {
            map.put("Success", false);
            map.put("data", cart);
        }
        return map;
    }

    @PutMapping("/update/{id}")
    public HashMap<String, Object> updateList(@RequestBody Cart cart, @PathVariable Long id){
        HashMap<String, Object> map = new HashMap<>();
        boolean update = cartServices.updateCart(id, cart);
        if(update){
            map.put("Success", true);
            map.put("Message", "cart updated");
        } else {
            map.put("Success", false);
            map.put("data", "Failed to update");
        }
        return map;
    }

    @DeleteMapping("/delete/{id}")
    public  HashMap<String, Object> deleteCart(Long id){
        HashMap<String,Object> map = new HashMap<>();
        boolean del = cartServices.deleteCart(id);
        if(del){
            map.put("Success", true);
            map.put("Message", "item deleted successfully");
        } else {
            map.put("Success", false);
            map.put("Message", "item  not found");
        }
        return map;
    }


}
