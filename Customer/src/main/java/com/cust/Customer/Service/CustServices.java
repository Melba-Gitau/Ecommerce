package com.cust.Customer.Service;

import com.cust.Customer.Model.Customer;
import com.cust.Customer.Repo.CustRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustServices {
    @Autowired
    CustRepo custRepo;

    public boolean create(Customer customer){
        try{
            custRepo.save(customer);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Customer> customerList(){
        return custRepo.findAll();
    }

    public boolean updateList(Long Id,Customer customer){
        Optional<Customer> oldCustomer = custRepo.findById(Id);
        if(oldCustomer.isPresent()){
            try{
                Customer updated = oldCustomer.get();
                updated.setName(customer.getName());
                updated.setContacts(customer.getContacts());
                updated.setLocation(customer.getLocation());
                custRepo.save(customer);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean deleteCustomer(Long Id){
        Optional<Customer> customer = custRepo.findById(Id);
        if(customer.isPresent()){
            custRepo.delete(customer.get());
            return true;
        }else{
            return false;
        }
    }
}
