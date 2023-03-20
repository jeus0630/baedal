package com.example.baemin.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDTO.CategoryCreateResponse create(CategoryDTO.CategoryCreateRequest req) {
        return convertToCategoryCreateResponseDTO(createCategoryFromRequest(req));
    }

    public List<CategoryDTO.CategoryReadResponse> read() {
        return readCategoryFromRequest().stream().map(
                category -> convertToCategoryReadResponse(category)
        ).collect(Collectors.toList());
    }

    public CategoryDTO.CategoryUpdateResponse update(
            Long id,
            CategoryDTO.CategoryUpdateRequest req
    ) {
        return convertToCategoryUpdateResponse(updateCategoryFromRequest(id, req));
    }

    public CategoryDTO.CategoryDeleteResponse delete(
            Long id
    ) {
        deleteCategoryFromRequest(id);
        return convertToCategoryDeleteResponseDTO(id);
    }

    private Category createCategoryFromRequest(CategoryDTO.CategoryCreateRequest req) {
        return this.categoryRepository.save(Category.builder()
                .name(req.getName())
                .build());
    }

    private CategoryDTO.CategoryCreateResponse convertToCategoryCreateResponseDTO(Category category) {
        return CategoryDTO.CategoryCreateResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    private List<Category> readCategoryFromRequest() {
        return this.categoryRepository.findAll();
    }

    private CategoryDTO.CategoryReadResponse convertToCategoryReadResponse(Category category) {
        return CategoryDTO.CategoryReadResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    private Category updateCategoryFromRequest(
            Long id,
            CategoryDTO.CategoryUpdateRequest req
    ) {
        Category category = findCategoryById(id);
        category.setName(req.getName());
        return this.categoryRepository.save(category);
    }

    private Category findCategoryById(Long id) {
        return this.categoryRepository.findById(id).get();
    }

    private CategoryDTO.CategoryUpdateResponse convertToCategoryUpdateResponse(
            Category category
    ) {
        return CategoryDTO.CategoryUpdateResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    private void deleteCategoryFromRequest(
            Long id
    ) {
        this.categoryRepository.delete(findCategoryById(id));
    }

    private CategoryDTO.CategoryDeleteResponse convertToCategoryDeleteResponseDTO(Long id) {
        return CategoryDTO.CategoryDeleteResponse.builder()
                .id(id)
                .build();
    }

}
