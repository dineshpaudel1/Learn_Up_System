package com.Learn_Up.System.Controllers.CategoryController;

import com.Learn_Up.System.Models.CategoryModel.Category;
import com.Learn_Up.System.Repositories.CategoryRepository.CategoryRepository;
import com.Learn_Up.System.Services.CategoryService.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@CrossOrigin("/*")

public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private CategoryServices categoryServices;


    @PostMapping("/addCategory")
    public ResponseEntity<String> addCategory(@RequestBody Category category) {
        return categoryServices.addCategory(category);
    }
}
