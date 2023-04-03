package com.example.baemin.domain.category;

import com.example.baemin.global.error.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

@Tag(name = "Category", description = "카테고리 API")
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "카테고리 생성 API", description = "새로운 카테고리를 생성하는 API")
    @PostMapping("")
    public ResponseEntity<CategoryDTO.Response> create(
            @RequestBody CategoryDTO.Request req) {
        CategoryDTO.Response res = this.categoryService.create(req);

        return ResponseEntity.created(URI.create("/" + res.getId())).body(res);
    }

    @Operation(summary = "카테고리 조회 API", description = "카테고리 전체를 조회하는 API")
    @GetMapping("")
    public ResponseEntity<List<CategoryDTO.Response>> read() {
        return ResponseEntity.ok(this.categoryService.read());
    }

    @Operation(summary = "카테고리 수정 API", description = "카테고리를 수정하는 API")
    @Parameter(name = "id", description = "카테고리 id", in = ParameterIn.PATH, required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 완료", content = @Content(schema = @Schema(implementation = CategoryDTO.Response.class))),
            @ApiResponse(responseCode = "404", description = "해당 id 값 조회 실패", content = @Content(schema = @Schema(implementation = ErrorDTO.CategoryNotFound.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO.Response> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO.Request req) {
        return ResponseEntity.ok(this.categoryService.update(id, req));
    }

    @Operation(summary = "카테고리 삭제 API", description = "카테고리를 삭제하는 API")
    @Parameter(name = "id", description = "카테고리 id", in = ParameterIn.PATH, required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO.DeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.categoryService.delete(id));
    }

}
