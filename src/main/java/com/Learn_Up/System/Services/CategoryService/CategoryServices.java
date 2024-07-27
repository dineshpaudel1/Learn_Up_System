package com.Learn_Up.System.Services.CategoryService;

import com.Learn_Up.System.Models.CategoryModel.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CategoryServices {
    ResponseEntity<String> addCategory(Category category);
}
