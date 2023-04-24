package com.example.users.Services;

import com.example.users.Model.Users;
import com.example.users.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  UserRepo userRepo;

  public boolean create(Users users){
      try{
          userRepo.save(users);
          return true;
      }catch(Exception e){
          e.printStackTrace();
          return false;
      }
  }
  public List<Users> usersList(){
      return userRepo.findAll();
  }

  public boolean usersUpdate(Long id, Users users){
      Optional<Users> oldUser = userRepo.findById(id);
      if(oldUser.isPresent()){
          try {
              Users updated = oldUser.get();
              updated.setName(users.getName());
              updated.setPhone(users.getPhone());
              updated.setPassword(users.getPassword());
              updated.setStatus(users.getStatus());
              userRepo.save(users);
              return true;
          }catch(Exception e){
              e.printStackTrace();
              return false;
          }
      }else{
          return false;
      }
  }

  public boolean deleteCustomer(Long id){
      Optional<Users> users = userRepo.findById(id);
      if(users.isPresent()){
          userRepo.delete(users.get());
          return true;
      }else{
          return false;
      }
  }
}
