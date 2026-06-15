package com.quizeffect.quiz_api.controller;

import com.quizeffect.quiz_api.dto.ImportQuizRequest;
import com.quizeffect.quiz_api.entity.Category;
import com.quizeffect.quiz_api.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PostMapping("/import")
    public String importBulk(@RequestBody List<ImportQuizRequest> requests) {
        categoryService.importBulkData(requests);
        return "Import dữ liệu hàng loạt thành công!";
    }
}