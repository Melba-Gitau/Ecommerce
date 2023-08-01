package com.mpesa.mpesaservice.Controller;

import com.mpesa.mpesaservice.Model.Mpesa;
import com.mpesa.mpesaservice.Service.MpesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("service")
public class MpesaController {
    @Autowired
    MpesaService mpesaService;

    @GetMapping("/read")
    public HashMap<String, Object> listMpesa(){
        HashMap<String, Object> map = new HashMap<>();
        List<Mpesa> read = mpesaService.readDetails();
        if(read.isEmpty()){
            map.put("Success", false);
            map.put("Message", "details not found");
        } else {
            map.put("Success", true);
            map.put("data", read);
        }
        return map;
    }

    @PutMapping("/update/{id}")
    public HashMap<String, Object> update(@RequestBody Mpesa mpesa, @PathVariable Long id){
        HashMap<String, Object> map = new HashMap<>();
        boolean update = mpesaService.updateMpesa(id, mpesa);
        if(update){
            map.put("Success", true);
            map.put("Message", "Details updated");
        } else {
            map.put("Success", false);
            map.put("data", "Failed to update");
        }
        return map;
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String, Object> map (@PathVariable Long id) {
        HashMap<String, Object> map = new HashMap<>();
        boolean del = mpesaService.deleteData(id);
        if (del) {
            map.put("Success", true);
            map.put("Message", "deleted successfully");
        } else {
            map.put("Success", false);
            map.put("data", "Failed to delete");
        }
        return map;
    }
}
