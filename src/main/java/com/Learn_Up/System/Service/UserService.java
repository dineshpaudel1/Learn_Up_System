package com.Learn_Up.System.Service;

import com.Learn_Up.System.Repository.UserRepository;
import com.Learn_Up.System.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

     public List<User> findAll(){
         return userRepository.findAll();
     }
     public User findById(long id){
         return userRepository.findById(id).orElse(null);
     }
     public User save(User user){
         return userRepository.save(user);
     }
     public void delete(long id){
         userRepository.deleteById(id);
     }
}
