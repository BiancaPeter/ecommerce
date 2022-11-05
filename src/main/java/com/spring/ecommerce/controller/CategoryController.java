package com.spring.ecommerce.controller;

import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(@Autowired CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/")
    public List<Category> getCategories(){
        return  categoryService.getCategories();
    }

    @PutMapping("/update/{categoryId}")
    public Category updateCategory(@RequestBody Category category,@PathVariable Long categoryId){
        return categoryService.updateCategory(category,categoryId);
    }



}
