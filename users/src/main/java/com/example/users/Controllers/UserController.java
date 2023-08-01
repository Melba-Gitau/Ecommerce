package com.example.users.Controllers;

import com.example.users.Model.Users;
import com.example.users.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public HashMap<String, Object> create (Users users){
        HashMap<String, Object> map = new HashMap<>();
        if(userService.create(users)){
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
        List <Users> users = userService.usersList();
        if(users.isEmpty()){
            map.put("Success",false);
            map.put("Message","user not found");
        }else{
            map.put("Success",true);
            map.put("data",users);
        }
        return map;
    }
    @PutMapping("/update/{id}")
    public HashMap<String, Object>  update (@RequestBody Users users, @PathVariable Long id){
        HashMap <String, Object> map = new HashMap<>();
        if(userService.usersUpdate(id, users)){
            map.put("Success", true);
            map.put("Message", "Customer details updated successfully");
        } else {
            map.put("Success", false);
            map.put("Message", "Failed to update.");
        }
        return map;
    }

    @DeleteMapping("/delete")
    public HashMap<String, Object> delete(@PathVariable("id") Long id) {
        HashMap<String, Object> map = new HashMap<>();
        boolean users = userService.deleteCustomer(id);
        if (users) {
            map.put("Success", true);
            map.put("Message", "User deleted successfully");
        } else {
            map.put("Success", false);
            map.put("Message", "User not found");
        }
        return map;
    }

}
