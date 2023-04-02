package com.example.baemin.domain.food;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Food", description = "음식 API")
@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @Operation(summary = "음식 생성 API", description = "레스토랑의 음식을 생성하는 API")
    @PostMapping("")
    public ResponseEntity<FoodDto.Response> create(
            @RequestBody FoodDto.Request req) {
        return ResponseEntity.created(URI.create("")).body(
                foodService.create(req)
        );
    }

    @Operation(summary = "음식 조회 API", description = "레스토랑에 등록된 음식을 전체 조회하는 API")
    @GetMapping("")
    public ResponseEntity<List<FoodDto.Response>> read() {
        return ResponseEntity.ok(foodService.read());
    }

    @Operation(summary = "음식 조회 API", description = "레스토랑에 등록된 특정 음식을 조회하는 API")
    @Parameter(name = "id", description = "음식 id", in = ParameterIn.PATH, required = true)
    @GetMapping("/{id}")
    public ResponseEntity<FoodDto.Response> read(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.read(id));
    }

    @Operation(summary = "음식 수정 API", description = "레스토랑에 등록된 특정 음식을 수정하는 API")
    @Parameter(name = "id", description = "음식 id", in = ParameterIn.PATH, required = true)
    @PutMapping("/{id}")
    public ResponseEntity<FoodDto.Response> update(
            @PathVariable Long id,
            @RequestBody FoodDto.Request req) {
        return ResponseEntity.ok(foodService.update(id, req));
    }

    @Operation(summary = "음식 삭제 API", description = "레스토랑에 등록된 특정 음식을 삭제하는 API")
    @Parameter(name = "id", description = "음식 id", in = ParameterIn.PATH, required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<FoodDto.DeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.delete(id));
    }

}
