package com.example.products.Controllers;

import com.example.products.Models.ProductDetails;
import com.example.products.Models.Stock;
import com.example.products.Services.StockServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    StockServices stockServices;
    @PostMapping("create")
    public HashMap<String, Object> createStock(@RequestBody Stock stock){
    HashMap <String, Object> map = new HashMap<>();
    boolean create = stockServices.create(stock);
    if(create){
        map.put("Success",true);
        map.put("Message","Details added successfully");
    }else{
        map.put("Success",false);
        map.put("Message","Failed to add Details");
    }
        return map;
    }

    @GetMapping("read")
        public HashMap<String,Object> readStock() {
        HashMap<String, Object> map = new HashMap<>();
        List<Stock> stock = stockServices.listOfStock();
        if (stock.isEmpty()) {
            map.put("Success", true);
            map.put("Message", "stock not found");
        } else {
            map.put("Success", false);
            map.put("data", stock);
        }
        return map;
    }

    @PutMapping("/update/{id}")
    public HashMap<String, Object> updateList(@RequestBody Stock stock, @PathVariable Long id){
        HashMap<String, Object> map = new HashMap<>();
        boolean update = stockServices.updateStock(id,stock);
        if(update){
            map.put("Success", true);
            map.put("Message", "stock not found");
        } else {
            map.put("Success", false);
            map.put("data", "Failed to update");
        }
        return map;
    }

    @DeleteMapping("/delete/{id}")
    public  HashMap<String, Object> deleteList(Long id){
        HashMap<String,Object> map = new HashMap<>();
        boolean del = stockServices.deleteStock(id);
        if(del){
            map.put("Success", true);
            map.put("Message", "Product deleted successfully");
        } else {
            map.put("Success", false);
            map.put("Message", "Product  not found");
        }
        return map;
    }

}

