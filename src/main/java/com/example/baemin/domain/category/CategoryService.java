package com.example.baemin.domain.category;

import com.example.baemin.global.error.ErrorCode;
import com.example.baemin.global.error.exception.BusinessException;
import com.example.baemin.domain.food.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final FoodRepository foodRepository;

    public CategoryDTO.Response create(CategoryDTO.Request req) {
        return convertToResponseDTO(categoryRepository.save(req.toEntity()));
    }

    public List<CategoryDTO.Response> read() {
        return categoryRepository.findAll().stream().map(
                category -> convertToResponseDTO(category)
        ).collect(Collectors.toList());
    }

    public CategoryDTO.Response update(
            Long id,
            CategoryDTO.Request req) {
        return convertToResponseDTO(categoryRepository.save(req.toEntity(id)));
    }

    public CategoryDTO.DeleteResponse delete(Long id) {
        deleteFood();
        deleteCategory(id);
        return convertToDeleteResponseDTO(id);
    }

    private CategoryDTO.Response convertToResponseDTO(Category category) {
        CategoryDTO.Response response = new CategoryDTO.Response();
        BeanUtils.copyProperties(category, response);
        return response;
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    private void deleteCategory(Long id) {
        categoryRepository.delete(findCategoryById(id));
    }

    private CategoryDTO.DeleteResponse convertToDeleteResponseDTO(Long id) {
        return CategoryDTO.DeleteResponse.builder()
                .id(id)
                .build();
    }

    private void deleteFood() {
        foodRepository.findAll().stream().forEach(food -> foodRepository.delete(food));
    }
}
