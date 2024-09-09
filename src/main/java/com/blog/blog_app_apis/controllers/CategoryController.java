package com.blog.blog_app_apis.controllers;

import com.blog.blog_app_apis.payloads.ApiResponse;
import com.blog.blog_app_apis.payloads.CategoryDto;
import com.blog.blog_app_apis.payloads.UserDto;
import com.blog.blog_app_apis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCat(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCatDto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createCatDto, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCat(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        CategoryDto updateCatDto = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(updateCatDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully!", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public  ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getUserById(@PathVariable Integer categoryId){
        return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
    }



}
