package com.blog.blog_app_apis.services;

import com.blog.blog_app_apis.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    //delete
    void deleteCategory(Integer categoryId);
    //get
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Integer categoryId);
}
