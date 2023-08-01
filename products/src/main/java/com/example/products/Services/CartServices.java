package com.example.products.Services;

import com.example.products.Models.Cart;
import com.example.products.Models.ProductDetails;
import com.example.products.Models.Stock;
import com.example.products.Repo.CartRepo;
import com.example.products.Repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServices {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    StockServices stockServices;
    @Autowired
    ProductServices productServices;
    public boolean create(Cart cart){
        try{
            cartRepo.save(cart);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public List<Map<String,Object>> listInCart(){
        List<Map<String,Object>> mapList=new ArrayList<>();
        List<Cart> productsList= cartRepo.findAll();
        for (Cart cart:productsList) {
         List<Stock> stocks=stockServices.getStockId(cart.getProductId());
         double price=stocks.get(0).getPrice();
         double total_amount= cart.getQuantity()*price;
         Optional<ProductDetails> product=productServices.getProduct(cart.getProductId());
         if(product.isPresent()){
             Map<String,Object>map=new HashMap<>();
             map.put("product",product);
             map.put("total_amount",total_amount);
             map.put("id",cart.getId());
             map.put("quantity",cart.getQuantity());
             map.put("price",price);
             mapList.add(map);
         }else{
             Map<String,Object>map=new HashMap<>();
             map.put("product","Not found");
             map.put("total_amount","null");
             map.put("id",cart.getId());
             mapList.add(map);
         }
        }
        return mapList;
    }

    public boolean updateCart(Long Id, Cart cart){
        Optional<Cart> oldCart = cartRepo.findById(Id);
        if (oldCart.isPresent()) {
            try {
                Cart updated = oldCart.get();
                updated.setProductId(cart.getProductId());
                updated.setStockId(cart.getStockId());
                cartRepo.save(cart);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean deleteCart(Long Id){
        Optional <Cart> oldCart = cartRepo.findById(Id);
        if(oldCart.isEmpty()){
            return false;
        }else{
            cartRepo.delete(oldCart.get());
            return true;
        }
    }
}
