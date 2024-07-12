package com.Learn_Up.System.Service;

import com.Learn_Up.System.Repository.UserRepository;
import com.Learn_Up.System.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileStorageService fileStorageService;
    private MultipartFile profilePicturePath;

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
    public User saveWithProfilePicturePath(User user, MultipartFile profilePicturePath) throws IOException {
        String filePath = fileStorageService.storeFile(profilePicturePath);
        user.setProfilePicturePath(filePath);
        return userRepository.save(user);
    }
}
