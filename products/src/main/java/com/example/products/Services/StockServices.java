package com.example.products.Services;

import com.example.products.Models.Stock;
import com.example.products.Repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<Stock> getStockId(Long id) {
        return stockRepo.findAllByProductid(id);
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
    public Map<String,Object> getStockBalance(Long productId){
        double quantity=0;
        List<Stock> stocks=stockRepo.findAllByProductid(productId);
        for (Stock stock:stocks) {
            quantity+=stock.getQuantity();
        }

        Map <String,Object> map=new HashMap<>();
        map.put("entries",stocks);
        map.put("total",quantity);
        return map;
    }

}
