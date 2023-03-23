package com.example.baemin.domain.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantDTO.Response create(RestaurantDTO.Request req) {
        return convertToResponseDTO(createRestaurantFromRequest(req));
    }

    public List<RestaurantDTO.Response> read() {
        return readRestaurantFromRequest().stream().map(
                restaurant -> convertToResponseDTO(restaurant)
        ).collect(Collectors.toList());
    }

    public RestaurantDTO.Response update(Long id, RestaurantDTO.Request req) {
        return convertToResponseDTO(updateRestaurantFromRequest(id, req));
    }

    public RestaurantDTO.DeleteResponse delete(Long id) {
        return convertToDeleteResponseDTO(deleteRestaurantFromRequest(id));
    }

    private Restaurant createRestaurantFromRequest(RestaurantDTO.Request req) {
        return this.restaurantRepository.save(
                Restaurant.builder()
                        .name(req.getName())
                        .location(req.getLocation())
                        .build()
        );
    }

    private List<Restaurant> readRestaurantFromRequest() {
        return this.restaurantRepository.findAllByIsActive(true);
    }

    private RestaurantDTO.Response convertToResponseDTO(Restaurant restaurant) {
        return RestaurantDTO.Response.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .build();
    }

    private Restaurant findRestaurantById(Long id) {
        return this.restaurantRepository.findById(id).get();
    }

    private Restaurant updateRestaurantFromRequest(Long id, RestaurantDTO.Request req) {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setName(req.getName());
        restaurant.setLocation(req.getLocation());
        return this.restaurantRepository.save(restaurant);
    }

    private Restaurant deleteRestaurantFromRequest(Long id) {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setIsActive(false);
        return this.restaurantRepository.save(restaurant);
    }

    private RestaurantDTO.DeleteResponse convertToDeleteResponseDTO(Restaurant restaurant) {
        return RestaurantDTO.DeleteResponse.builder()
                .id(restaurant.getId())
                .build();
    }

}
