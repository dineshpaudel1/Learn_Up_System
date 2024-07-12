package com.Learn_Up.System.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {
    @Value("${upload.path}")
    private String uploadPath;

    public String storeFile(MultipartFile file) throws IOException {
        String FileName = file.getOriginalFilename();

        Path path = Paths.get(uploadPath + File.separator + FileName);
        Files.createDirectories(path.getParent());
        Files.copy(file.getInputStream(), path);
        return path.toString();
    }
}
