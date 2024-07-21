    package com.Learn_Up.System.Services.UserServices;

    import com.Learn_Up.System.Entities.UsersEntity.UsersEntity;
    import com.Learn_Up.System.Models.UsersModel.Users;
    import com.Learn_Up.System.Models.UsersModel.UsersResponse;
    import com.Learn_Up.System.CustomMessage;
    import com.Learn_Up.System.Repositories.UserRepository.UsersRepository;
    import com.amazonaws.util.IOUtils;
    import org.springframework.beans.BeanUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.File;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.util.List;
    import java.util.Optional;
    import java.util.stream.Collectors;

    @Service
    public class UsersServiceImplementation implements UsersService {
        @Autowired
        private UsersRepository usersRepository;

        @Value("${project.image}")
        private String path;

        public ResponseEntity<String> addUser(Users users, MultipartFile file) {
            try {
                String fileName = file.getOriginalFilename();
                String filePath = path + File.separator + fileName;
                File f = new File(path);
                if (!f.exists()) {
                    f.mkdir();
                }
                Files.copy(file.getInputStream(), Paths.get(filePath));
                UsersEntity usersEntity = UsersEntity.builder().name(users.getName())
                        .email(users.getEmail())
                        .password(users.getPassword())
                        .role(users.getRole())
                        .profile("/users/photo?fileName=" + fileName)
                        .build();
                usersRepository.save(usersEntity);
                return new ResponseEntity<>("Course added successfully", HttpStatus.OK);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public ResponseEntity<List<UsersResponse>> displayUsers() {
            List<UsersEntity> usersEntity = usersRepository.findAll();
            List<UsersResponse> usersList = usersEntity.stream()
                    .map(courseData -> {
                        UsersResponse usersResponse = new UsersResponse();
                        BeanUtils.copyProperties(courseData, usersResponse);
                        return usersResponse;
                    }).collect(Collectors.toList());
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        }

        @Override
        public ResponseEntity<?> returnPhoto(String fileName) {
            String filePath = path + File.separator + fileName;
            try {
                InputStream inputStream = new FileInputStream(filePath);
                byte[] out = IOUtils.toByteArray(inputStream);
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("charset", "utf-8");
                responseHeaders.setContentType(MediaType.IMAGE_JPEG);
                return new ResponseEntity<>(out, responseHeaders, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(400).body(CustomMessage.builder().message(e.getMessage()));
            }
        }

        @Override
        public ResponseEntity<String>deleteUsers(Long userId) {
            try{
                if(usersRepository.existsById(userId)){
                    usersRepository.deleteById(userId);
                    return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
                }
            }catch (Exception e){
                return new ResponseEntity<>("User not found for delete" + e.getMessage(), HttpStatus.NOT_FOUND);
            }
        }

        @Override
        public ResponseEntity<String>updateUsers(Long usersId, Users updateUsers, MultipartFile file) {
            try{
                Optional<UsersEntity> optionalUsers=usersRepository.findById(usersId);
                if (optionalUsers.isPresent()){
                    UsersEntity existingUsers = optionalUsers.get();

                    existingUsers.setName(updateUsers.getName());
                    existingUsers.setEmail(updateUsers.getEmail());
                    existingUsers.setPassword(updateUsers.getPassword());
                    existingUsers.setRole(updateUsers.getRole());

                    if(file != null){
                        String fileName = file.getOriginalFilename();
                        String filePath = path + File.separator + fileName;

                        Files.copy(file.getInputStream(), Paths.get(filePath));
                        existingUsers.setProfile("/users/photo?fileName=" + fileName);
                    }
                    usersRepository.save(existingUsers);
                    return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Course not found with this id: "+usersId, HttpStatus.NOT_FOUND);
                }
            }catch (Exception e){
                return new ResponseEntity<>("Error while updating users" +e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
