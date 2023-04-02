package com.example.baemin.domain.category;

import com.example.baemin.global.error.ErrorCode;
import com.example.baemin.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDTO.Response create(CategoryDTO.Request req) {
        return convertToResponseDTO(createCategory(req));
    }

    public List<CategoryDTO.Response> read() {
        return readCategory().stream().map(
                category -> convertToResponseDTO(category)
        ).collect(Collectors.toList());
    }

    public CategoryDTO.Response update(
            Long id,
            CategoryDTO.Request req) {
        return convertToResponseDTO(updateCategory(id, req));
    }

    public CategoryDTO.DeleteResponse delete(Long id) {
        deleteCategory(id);
        return convertToDeleteResponseDTO(id);
    }

    private Category createCategory(CategoryDTO.Request req) {
        return categoryRepository.save(req.toEntity());
    }

    private CategoryDTO.Response convertToResponseDTO(Category category) {
        return CategoryDTO.Response.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    private List<Category> readCategory() {
        return categoryRepository.findAll();
    }

    private Category updateCategory(
            Long id,
            CategoryDTO.Request req) {
        findCategoryById(id);
        return categoryRepository.save(req.toEntity(id));
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
}
