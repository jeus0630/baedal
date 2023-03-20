package com.example.baemin.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<CategoryDTO.CategoryCreateResponse> create(
            @RequestBody CategoryDTO.CategoryCreateRequest req) {
        CategoryDTO.CategoryCreateResponse res = this.categoryService.create(req);

        return ResponseEntity.created(URI.create("/" + res.getId())).body(res);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO.CategoryReadResponse>> read() {
        return ResponseEntity.ok(this.categoryService.read());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO.CategoryUpdateResponse> update(
            @PathVariable Long id,
            @RequestBody CategoryDTO.CategoryUpdateRequest req) {
        return ResponseEntity.ok(this.categoryService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO.CategoryDeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.categoryService.delete(id));
    }

}
