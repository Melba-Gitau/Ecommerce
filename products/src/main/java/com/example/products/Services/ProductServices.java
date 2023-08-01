package com.example.products.Services;

import com.example.products.Models.ProductDetails;
import com.example.products.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    @Autowired
    ProductRepo productRepo;

    public boolean create(ProductDetails productDetails) {
        try {
            productRepo.save(productDetails);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Optional<ProductDetails> getProduct(long id){
        return productRepo.findById(id);
    }
    public List<ProductDetails> productList(){
         return productRepo.findAll();
    }

    public boolean updateProducts(Long id, ProductDetails productDetails){
        Optional<ProductDetails> oldProduct = productRepo.findById(id);
        if(oldProduct.isPresent()){
            try{
                ProductDetails updated =oldProduct.get();
                updated.setProduct_name(productDetails.getProduct_name());
                updated.setDescription(productDetails.getDescription());
                productRepo.save(productDetails);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean deleteProduct(Long id){
        Optional<ProductDetails> product = productRepo.findById(id);
        if(product.isPresent()){
            productRepo.delete(product.get());
            return true;
        }else{
            return false;
        }
    }
}
