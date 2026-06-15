package com.quizeffect.quiz_api.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizeffect.quiz_api.dto.ImportQuizRequest;
import com.quizeffect.quiz_api.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;
    public DataInitializer(CategoryService categoryService, ObjectMapper objectMapper) {
        this.categoryService = categoryService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryService.getAllCategories().isEmpty()) {
            System.out.println(">>> Database trống! Bắt đầu tự động nạp dữ liệu mồi (Seed Data)...");
            
            try {
                // Đọc file json 
                InputStream inputStream = TypeReference.class.getResourceAsStream("/quiz_master_data.json");
                
                if (inputStream != null) {
                    List<ImportQuizRequest> requests = objectMapper.readValue(inputStream, new TypeReference<List<ImportQuizRequest>>(){});
                    categoryService.importBulkData(requests);
                    System.out.println(">>> Tự động nạp dữ liệu mồi thành công dữ liệu!");
                } else {
                    System.out.println(">>> Không tìm thấy file quiz_master_data.json trong thư mục resources!");
                }
            } catch (Exception e) {
                System.out.println(">>> Lỗi trong quá trình nạp dữ liệu tự động: " + e.getMessage());
            }
        } else {
            System.out.println(">>> Database đã có sẵn dữ liệu, bỏ qua bước nạp dữ liệu mồi.");
        }
    }
}