package com.example.products.Controllers;

import com.example.products.Models.Category;
import com.example.products.Services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServices categoryServices;
    @PostMapping("/create")
    public HashMap<String, Object> create(@RequestBody Category category){
        HashMap <String, Object> map = new HashMap<>();
        if(categoryServices.create(category)){
            map.put("Success",true);
            map.put("Message","Details added successfully");
        }else{
            map.put("Success",false);
            map.put("Message","Failed to add Details");
        }
        return map;
    }

    @GetMapping("/list")
    public HashMap< String, Object> list(){
        HashMap<String, Object> map = new HashMap<>();
        List <Category> category= categoryServices.listCategory();
        if(category.isEmpty()){
            map.put("Success",false);
            map.put("Message"," not found");
        }else{
            map.put("Success",true);
            map.put("data",category);
        }
        return map;
    }

    @PutMapping("/update{id}")
    public HashMap< String, Object> update(@RequestBody Category category, @PathVariable  Long id){
        HashMap<String, Object> map = new HashMap<>();
        if(categoryServices.updateCategory(id,category)){
            map.put("Success", true);
            map.put("Message", "details updated successfully");
        } else {
            map.put("Success", false);
            map.put("Message", "Failed to update.");
        }
        return map;
    }

    @DeleteMapping("/delete{id}")
    public HashMap< String, Object> delete(@PathVariable Long id){
        HashMap<String, Object> map = new HashMap<>();
        if(categoryServices.delete(id)){
            map.put("Success", true);
            map.put("Message", " Category deleted successfully");
        } else {
            map.put("Success", false);
            map.put("Message", "Category  not found");
        }
        return map;
    }
}
