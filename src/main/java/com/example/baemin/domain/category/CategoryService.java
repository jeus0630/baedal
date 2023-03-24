package com.example.baemin.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDTO.Response create(CategoryDTO.Request req) {
        return convertToResponseDTO(createCategoryFromRequest(req));
    }

    public List<CategoryDTO.Response> read() {
        return readCategoryFromRequest().stream().map(
                category -> convertToResponseDTO(category)
        ).collect(Collectors.toList());
    }

    public CategoryDTO.Response update(
            Long id,
            CategoryDTO.Request req) {
        return convertToResponseDTO(updateCategoryFromRequest(id, req));
    }

    public CategoryDTO.DeleteResponse delete(Long id) {
        deleteCategoryFromRequest(id);
        return convertToDeleteResponseDTO(id);
    }

    private Category createCategoryFromRequest(CategoryDTO.Request req) {
        return this.categoryRepository.save(Category.builder()
                .name(req.getName())
                .build());
    }

    private CategoryDTO.Response convertToResponseDTO(Category category) {
        return CategoryDTO.Response.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    private List<Category> readCategoryFromRequest() {
        return this.categoryRepository.findAll();
    }

    private Category updateCategoryFromRequest(
            Long id,
            CategoryDTO.Request req) {
        Category category = findCategoryById(id);
        category.setName(req.getName());
        return this.categoryRepository.save(category);
    }

    private Category findCategoryById(Long id) {
        return this.categoryRepository.findById(id).get();
    }

    private void deleteCategoryFromRequest(Long id) {
        this.categoryRepository.delete(findCategoryById(id));
    }

    private CategoryDTO.DeleteResponse convertToDeleteResponseDTO(Long id) {
        return CategoryDTO.DeleteResponse.builder()
                .id(id)
                .build();
    }
}
