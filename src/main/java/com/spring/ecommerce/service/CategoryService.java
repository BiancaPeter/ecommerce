package com.spring.ecommerce.service;

import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(@Autowired CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        Category foundedCategory = categoryRepository.findByName(category.getName());
        if (foundedCategory == null) {
            return categoryRepository.save(category);
        } else {
            throw new ResponseStatusException(HttpStatus.CREATED, "category already exists");
        }
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
    public Category updateCategory(Category category,Long categoryId){
        Category foundedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"the order you want to update was not found"));
        foundedCategory.setDescription(category.getDescription());
        foundedCategory.setName(category.getName());
        return categoryRepository.save(foundedCategory);
    }

}
