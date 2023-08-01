package com.example.products.Controllers;

import com.example.products.Models.ProductDetails;
import com.example.products.Services.ProductServices;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductServices productServices;
    @PostMapping("/create")
    public HashMap<String, Object> create(@RequestBody ProductDetails productDetails){
        HashMap<String, Object> map = new HashMap<>();
        if(productServices.create(productDetails)){
            map.put("Success",true);
            map.put("Message","Details added successfully");
        }else{
            map.put("Success",false);
            map.put("Message","Failed to add Details");
        }
        return map;
    }


    @GetMapping("list")
    public HashMap<String, Object> read(){
        HashMap<String, Object> map = new HashMap<>();
        List<ProductDetails> product= productServices.productList();
        if(product.isEmpty()){
            map.put("Success",false);
            map.put("Message","product not found");
        }else{
            map.put("Success",true);
            map.put("data",product);
        }
        return map;
    }

    @PutMapping("/update/{id}")
    public HashMap<String, Object> update(@RequestBody ProductDetails productDetails, @PathVariable Long id){
        HashMap<String, Object> map = new HashMap<>();
        if(productServices.updateProducts(id,productDetails)){
            map.put("Success", true);
            map.put("Message", "Product details updated successfully");
        } else {
            map.put("Success", false);
            map.put("Message", "Failed to update.");
        }
        return map;
    }
    @DeleteMapping("/delete/{id}")
    public HashMap<String, Object> delete(@PathVariable("id") Long Id) {
        HashMap<String, Object> map = new HashMap<>();
        boolean product = productServices.deleteProduct(Id);
        if (product) {
            map.put("Success", true);
            map.put("Message", "Product deleted successfully");
        } else {
            map.put("Success", false);
            map.put("Message", "Product  not found");
        }
        return map;
    }

}
