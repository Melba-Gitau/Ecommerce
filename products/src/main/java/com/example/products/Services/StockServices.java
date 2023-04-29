package com.example.products.Services;

import com.example.products.Models.Stock;
import com.example.products.ProductRepo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServices {
    @Autowired
    StockRepo stockRepo;

    public boolean create(Stock stock){
        try{
            stockRepo.save(stock);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Stock> listOfStock(){
        return stockRepo.findAll();
    }

    public boolean updateStock(Long id, Stock stock){
        Optional<Stock> oldStock = stockRepo.findById(id);
        if(oldStock.isPresent()){
            try{
                Stock updated = oldStock.get();
                updated.setPrice(stock.getPrice());
                updated.setQuantity(stock.getQuantity());
                stockRepo.save(stock);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean deleteStock(Long id){
        Optional<Stock> stock = stockRepo.findById(id);
        if(stock.isPresent()){
            stockRepo.delete(stock.get());
            return true;
        }else{
            return false;
        }
    }

}
