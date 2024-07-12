package com.Learn_Up.System.Controller;

import com.Learn_Up.System.Entity.User;
import com.Learn_Up.System.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    //for getting all user details
    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }
    //for getting specific details of user
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userService.findById(id);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/uregister")
    public ResponseEntity<User> createUser(@RequestParam("name") String name,
                                           @RequestParam("email") String email,
                                           @RequestParam("password") String password,
                                           @RequestParam("role") String role,
                                           @RequestParam("bio") String bio,
                                           @RequestParam(value = "profilePicturePath", required = false) MultipartFile profilePicturePath) throws IOException {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setBio(bio);
        user.setProfilePicturePath(String.valueOf(profilePicturePath));

        if (profilePicturePath != null) {
            user = userService.saveWithProfilePicturePath(user, profilePicturePath);
        } else {
            user = userService.save(user);
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestParam("name") String name,
                                           @RequestParam("email") String email,
                                           @RequestParam("password") String password,
                                           @RequestParam("role") String role,
                                           @RequestParam("bio") String bio,
                                           @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture) throws IOException {
        User user = userService.findById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);
            user.setBio(bio);

            if (profilePicture != null) {
                user = userService.saveWithProfilePicturePath(user, profilePicture);
            } else {
                user = userService.save(user);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }
