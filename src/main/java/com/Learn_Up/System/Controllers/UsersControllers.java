package com.Learn_Up.System.Controllers;

import com.Learn_Up.System.Models.Users;
import com.Learn_Up.System.Models.UsersResponse;
import com.Learn_Up.System.Services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersControllers {

    @Autowired
    private ObjectMapper mapper;

    @Value("${project.image}")
    private String path;

    @Autowired
    private UsersService usersService;


    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestParam("users")String data,@RequestParam("file") MultipartFile file) throws IOException {
        Users users = mapper.readValue(data, Users.class);
        return usersService.addUser(users,file);
    }
    @GetMapping
    public ResponseEntity<List<UsersResponse>>displayUsers(){
    return usersService.displayUsers();
    }
    @GetMapping("/photo")
    public ResponseEntity<?>displayPhoto(@RequestParam("fileName")String fileName){
        return usersService.returnPhoto(fileName);
    }
    @DeleteMapping("/delete/{usersId}")
    public ResponseEntity<?>deleteUser(@PathVariable Long usersId){
        return usersService.deleteUsers(usersId);
    }
    @PostMapping("/update/{usersId}")
        public ResponseEntity<String>updateUsers(@PathVariable Long usersId,@RequestParam("users")String data,
                                                 @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        Users users = mapper.readValue(data, Users.class);
        return usersService.updateUsers(usersId,users,file);
        }


}
