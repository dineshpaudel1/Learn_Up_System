package com.Learn_Up.System.Services.CategoryService;

import com.Learn_Up.System.Entities.CategoryEntity.CategoryEntity;
import com.Learn_Up.System.Models.CategoryModel.Category;
import com.Learn_Up.System.Repositories.CategoryRepository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CategoryServicesImplementation implements CategoryServices {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<String>addCategory(Category category) {
        CategoryEntity categoryEntity = CategoryEntity.builder().category_name(category.getCategory_name()).build();
        categoryRepository.save(categoryEntity);
        return new ResponseEntity("Category added successfully", HttpStatus.OK);
    }
}
