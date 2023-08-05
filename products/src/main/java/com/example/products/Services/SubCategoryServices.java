package com.example.products.Services;

import com.example.products.Models.Category;
import com.example.products.Models.Subcategory;
import com.example.products.Repo.SubcategoryRepo;

import java.util.List;
import java.util.Optional;

public class SubCategoryServices {
    SubcategoryRepo subcategoryRepo;
    public boolean create(Subcategory subcategory) {
        try {
            subcategoryRepo.save(subcategory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Subcategory> get(Long id){
        return subcategoryRepo.findById(id);
    }

    public List<Subcategory> list(){
        return subcategoryRepo.findAll();
    }
    public boolean update(Long id,Subcategory subcategory) {
        Optional<Subcategory> old = subcategoryRepo.findById(id);
        if (old.isPresent()) {
            try {
                Subcategory updated = old.get();
                updated.setCategory_id(subcategory.getName());
                updated.setName(subcategory.getName());
                updated.setDescription(subcategory.getDescription());
                subcategoryRepo.save(subcategory);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean delete(Long id){
        Optional<Subcategory> sub = subcategoryRepo.findById(id);
        if(sub.isPresent()){
            subcategoryRepo.delete(sub.get());
            return true;
        }else{
            return false;
        }
    }
}
