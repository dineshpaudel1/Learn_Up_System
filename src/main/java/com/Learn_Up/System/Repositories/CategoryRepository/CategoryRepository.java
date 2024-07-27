package com.Learn_Up.System.Repositories.CategoryRepository;

import com.Learn_Up.System.Entities.CategoryEntity.CategoryEntity;
import com.Learn_Up.System.Models.CategoryModel.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
