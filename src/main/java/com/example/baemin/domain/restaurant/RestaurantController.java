package com.example.baemin.domain.restaurant;

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
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("")
    public ResponseEntity<RestaurantDTO.RestaurantCreateResponse> create(@RequestBody RestaurantDTO.RestaurantCreateRequest req) {
        RestaurantDTO.RestaurantCreateResponse res = this.restaurantService.create(req);
        return ResponseEntity.created(URI.create("" + res.getId())).body(res);
    }

    @GetMapping("")
    public ResponseEntity<List<RestaurantDTO.RestaurantReadResponse>> read() {
        return ResponseEntity.ok(this.restaurantService.read());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO.RestaurantUpdateResponse> update(
            @PathVariable Long id,
            @RequestBody RestaurantDTO.RestaurantUpdateRequest req
    ) {
        return ResponseEntity.ok(this.restaurantService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestaurantDTO.RestaurantDeleteResponse> delete(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.restaurantService.delete(id));
    }

}
