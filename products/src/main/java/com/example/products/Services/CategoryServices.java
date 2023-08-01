package com.example.products.Services;

import com.example.products.Models.Category;
import com.example.products.Models.ProductDetails;
import com.example.products.Repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServices {
    @Autowired
    CategoryRepo categoryRepo;

    public boolean create(Category category) {
        try {
            categoryRepo.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Category> getCategory(Long id){
         return categoryRepo.findById(id);
    }

    public List<Category> listCategory(){
        return categoryRepo.findAll();
    }
    public boolean updateCategory(Long id,Category category) {
        Optional<Category> oldCategory = categoryRepo.findById(id);
        if (oldCategory.isPresent()) {
            try {
                Category updated = oldCategory.get();
                updated.setName(category.getName());
                categoryRepo.save(category);
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
        Optional<Category> category = categoryRepo.findById(id);
        if(category.isPresent()){
            categoryRepo.delete(category.get());
            return true;
        }else{
            return false;
        }
    }
}
