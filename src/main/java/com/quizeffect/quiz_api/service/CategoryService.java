package com.quizeffect.quiz_api.service;

import com.quizeffect.quiz_api.dto.ImportQuizRequest;
import com.quizeffect.quiz_api.entity.Answer;
import com.quizeffect.quiz_api.entity.Category;
import com.quizeffect.quiz_api.entity.Question;
import com.quizeffect.quiz_api.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public void importBulkData(List<ImportQuizRequest> requests) {
        for (ImportQuizRequest req : requests) {
            // 1. Kiểm tra môn học tồn tại chưa, nếu chưa thì tạo mới
            Category category = categoryRepository.findByName(req.getName())
                    .orElseGet(() -> {
                        Category newCat = new Category();
                        newCat.setName(req.getName());
                        newCat.setDescription(req.getDescription());
                        return categoryRepository.save(newCat);
                    });

            // 2. Chuyển đổi dữ liệu từ DTO sang Entity Câu hỏi và Đáp án
            if (req.getQuestions() != null) {
                for (ImportQuizRequest.QuestionDTO qDto : req.getQuestions()) {
                    Question question = new Question();
                    question.setContent(qDto.getContent());
                    question.setDifficulty(qDto.getDifficulty());
                    question.setCategory(category); // Gán khóa ngoại môn học

                    List<Answer> answers = new ArrayList<>();
                    if (qDto.getAnswers() != null) {
                        for (ImportQuizRequest.AnswerDTO aDto : qDto.getAnswers()) {
                            Answer answer = new Answer();
                            answer.setContent(aDto.getContent());
                            answer.setCorrect(aDto.isCorrect());
                            answer.setQuestion(question); // Gán khóa ngoại câu hỏi
                            answers.add(answer);
                        }
                    }
                    // Kích hoạt mối quan hệ Cascade ngược để tự lưu câu hỏi kèm đáp án
                    question.setAnswers(answers);
                    
                    // Thêm câu hỏi vào danh mục môn học
                    if (category.getQuestions() == null) {
                        category.setQuestions(new ArrayList<>());
                    }
                    category.getQuestions().add(question);
                }
            }
            // Lưu lại toàn bộ cây dữ liệu của môn học đó xuống DB
            categoryRepository.save(category);
        }
    }
}