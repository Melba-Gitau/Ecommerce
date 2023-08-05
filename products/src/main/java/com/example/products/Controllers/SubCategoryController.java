package com.example.products.Controllers;

import com.example.products.Models.Category;
import com.example.products.Models.Subcategory;
import com.example.products.Services.CategoryServices;
import com.example.products.Services.SubCategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/subcategory")
public class SubCategoryController {
    @Autowired
    SubCategoryServices subCategoryServices;
    @PostMapping("/create")
    public HashMap<String, Object> create(@RequestBody Subcategory subcategory){
        HashMap <String, Object> map = new HashMap<>();
        if(subCategoryServices.create(subcategory)){
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
        List<Subcategory> subcategory= subCategoryServices.list();
        if(subcategory.isEmpty()){
            map.put("Success",false);
            map.put("Message"," not found");
        }else{
            map.put("Success",true);
            map.put("data",subcategory);
        }
        return map;
    }

    @PutMapping("/update{id}")
    public HashMap< String, Object> update(@RequestBody Subcategory subcategory, @PathVariable Long id){
        HashMap<String, Object> map = new HashMap<>();
        if(subCategoryServices.update(id,subcategory)){
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
        if(subCategoryServices.delete(id)){
            map.put("Success", true);
            map.put("Message", " Category deleted successfully");
        } else {
            map.put("Success", false);
            map.put("Message", "Category  not found");
        }
        return map;
    }
}
