package com.cust.Customer.Controller;

import com.cust.Customer.Model.Customer;
import com.cust.Customer.Service.CustServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustController {

    @Autowired
    CustServices custServices;

    @PostMapping("/create")
    public HashMap<String, Object> create(@RequestBody Customer customer){
        HashMap<String, Object> map = new HashMap<>();
        if(custServices.create(customer)){
            map.put("Success",true);
            map.put("Message","Details added successfully");
        }else{
            map.put("Success",false);
            map.put("Message","Failed to add Details");
        }
        return map;
    }

    @GetMapping("/read")
    public HashMap<String, Object> read(){
        HashMap<String, Object> map = new HashMap<>();
        List<Customer> customer = custServices.customerList();
        if(customer.isEmpty()){
            map.put("Success",false);
            map.put("Message","customer not found");
        }else{
            map.put("Success",true);
            map.put("data",customer);
            }
            return map;
        }

    @PutMapping("/update/{id}")
    public Map<String,Object> update(@RequestBody Customer customer, @PathVariable Long Id){
        HashMap <String, Object> map = new HashMap<>();
        if(custServices.updateList(Id,customer)){
            map.put("Success", true);
            map.put("Message", "Customer details updated successfully");
        } else {
            map.put("Success", false);
            map.put("Message", "Failed to update.");
        }
        return map;
    }
    @DeleteMapping("/delete/{id}")
    public HashMap<String, Object> delete(@PathVariable("id") Long Id) {
        HashMap<String, Object> map = new HashMap<>();
        boolean customer = custServices.deleteCustomer(Id);
        if (customer) {
            map.put("Success", true);
            map.put("Message", "Customer deleted successfully");
        } else {
            map.put("Success", false);
            map.put("Message", "Customer not found");
        }
        return map;
    }
}
