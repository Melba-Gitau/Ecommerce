package com.example.products.Controllers;

import com.example.products.Models.ProductDetails;
import com.example.products.Models.Stock;
import com.example.products.Services.StockServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("getStockBalance/{productId}")
    public Map<String,Object> getStockBalance(@PathVariable Long productId){
        return  stockServices.getStockBalance(productId);
    }
    @GetMapping("/ip")
    public Map<String, Object> getClientIP(@RequestHeader(value = "X-Forwarded-For", required = false) String forwardedForHeader,
                                           HttpServletRequest request, @RequestBody Stock stock) {
        String clientIP = "";

        if (forwardedForHeader != null) {
            // Extract the first IP address from the X-Forwarded-For header
            clientIP = forwardedForHeader.split(",")[0].trim();
        }

        if (clientIP.isEmpty()) {
            // If X-Forwarded-For header is absent, retrieve the IP address from the request
            clientIP = request.getRemoteAddr();
        }
        Map<String,Object> map=new HashMap<>();
        map.put("client ip is",clientIP);
        map.put("cart items",stock);

        return map;
    }

}

