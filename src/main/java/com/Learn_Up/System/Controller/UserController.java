package com.Learn_Up.System.Controller;

import com.Learn_Up.System.Entity.User;
import com.Learn_Up.System.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/uregister")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody User user){
        User save = userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
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
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user){
        User userupdate = userService.findById(id);
        if(userupdate != null){
            userupdate.setName(user.getName());
            userupdate.setEmail(user.getEmail());
            userupdate.setPassword(user.getPassword());
            userupdate.setRole(user.getRole());
            userupdate.setProfilePicture(user.getProfilePicture());
            userupdate.setBio(user.getBio());
            return new ResponseEntity<>(userService.save(userupdate), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }
