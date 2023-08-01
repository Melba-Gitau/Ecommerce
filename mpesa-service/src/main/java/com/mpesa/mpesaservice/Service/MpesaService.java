package com.mpesa.mpesaservice.Service;

import com.mpesa.mpesaservice.Model.Mpesa;
import com.mpesa.mpesaservice.Repository.MpesaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MpesaService {
    @Autowired
    MpesaRepo mpesaRepo;
    //to create
    public boolean save(Mpesa mpesa){
        try{
            mpesaRepo.save(mpesa);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //to read
    public List<Mpesa> readDetails(){
        return mpesaRepo.findAll();
    }

    public Optional<Mpesa> findByMerchantIdAndCheckoutId(String merchantId, String checkoutId){
        return mpesaRepo.findByMerchantIdAndCheckoutId(merchantId, checkoutId);
    }

    //to update
    public boolean updateMpesa(Long id, Mpesa mpesa){
        Optional<Mpesa> data = mpesaRepo.findById(id);
            if(data.isPresent()){
                Mpesa updated = data.get();
                updated.setUsername(mpesa.getUsername());
                updated.setPhone(mpesa.getPhone());
                updated.setAmount(mpesa.getAmount());
                updated.setCheckoutId(mpesa.getCheckoutId());
                updated.setMerchantId(mpesa.getMerchantId());
                updated.setTransactionCode(mpesa.getTransactionCode());
                updated.setStatus(mpesa.getStatus());
                mpesaRepo.save(mpesa);
                return true;
            }else{
                return false;
            }
    }

    //to delete
    public boolean deleteData(Long id){
        Optional<Mpesa> data = mpesaRepo.findById(id);
        if(data.isPresent()){
            mpesaRepo.delete(data.get());
            return true;
        }else{
            return false;
        }
    }
}
