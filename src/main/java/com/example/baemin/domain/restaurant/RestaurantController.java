package com.example.baemin.domain.restaurant;

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

@Tag(name = "Restaurant", description = "레스토랑 API")
@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "레스토랑 생성 API", description = "새로운 레스토랑을 생성하는 API")
    @PostMapping("")
    public ResponseEntity<RestaurantDTO.Response> create(@RequestBody RestaurantDTO.Request req) {
        RestaurantDTO.Response res = this.restaurantService.create(req);
        return ResponseEntity.created(URI.create("" + res.getId())).body(res);
    }

    @Operation(summary = "레스토랑 조회 API", description = "전체 레스토랑을 조회하는 API")
    @GetMapping("")
    public ResponseEntity<List<RestaurantDTO.Response>> read() {
        return ResponseEntity.ok(this.restaurantService.read());
    }

    @Operation(summary = "레스토랑 조회 API", description = "특정 레스토랑을 수정하는 API")
    @Parameter(name = "id", description = "레스토랑 id", in = ParameterIn.PATH, required = true)
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO.Response> update(
            @PathVariable Long id,
            @RequestBody RestaurantDTO.Request req) {
        return ResponseEntity.ok(this.restaurantService.update(id, req));
    }

    @Operation(summary = "레스토랑 삭제 API", description = "특정 레스토랑을 삭제하는 API")
    @Parameter(name = "id", description = "레스토랑 id", in = ParameterIn.PATH, required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<RestaurantDTO.DeleteResponse> delete(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.restaurantService.delete(id));
    }

}
