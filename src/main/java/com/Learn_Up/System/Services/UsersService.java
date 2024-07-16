package com.Learn_Up.System.Services;

import com.Learn_Up.System.Models.Users;
import com.Learn_Up.System.Models.UsersResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface UsersService {
    ResponseEntity<String> addUser(Users users, MultipartFile file);
    ResponseEntity<List<UsersResponse>> displayUsers();
    ResponseEntity<?>returnPhoto(String fileName);
    ResponseEntity<String>deleteUsers(Long userId);
    ResponseEntity<String>updateUsers(Long usersId, Users updatedUsers, MultipartFile file);

}

