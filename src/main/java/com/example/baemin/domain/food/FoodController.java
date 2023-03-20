package com.example.baemin.domain.food;

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
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping("")
    public ResponseEntity<FoodDto.FoodCreateResponse> create(
            @RequestBody FoodDto.FoodCreateRequest req
    ) {
        return ResponseEntity.created(URI.create("")).body(
                foodService.create(req)
        );
    }

    @GetMapping("")
    public ResponseEntity<List<FoodDto.FoodReadResponse>> read() {
        return ResponseEntity.ok(this.foodService.read());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDto.FoodReadResponse> read(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodDto.FoodUpdateResponse> update(
            @PathVariable Long id,
            @RequestBody FoodDto.FoodUpdateRequest req
    ) {
        return ResponseEntity.ok(foodService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FoodDto.FoodDeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.delete(id));
    }

}
