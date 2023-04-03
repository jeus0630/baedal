package com.example.baemin.domain.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantDTO.Response create(RestaurantDTO.Request req) {
        return convertToResponseDTO(restaurantRepository.save(req.toEntity()));
    }

    public List<RestaurantDTO.Response> read() {
        return readRestaurantFromRequest().stream().map(
                restaurant -> convertToResponseDTO(restaurant)
        ).collect(Collectors.toList());
    }

    public RestaurantDTO.Response update(Long id, RestaurantDTO.Request req) {
        return convertToResponseDTO(restaurantRepository.save(req.toEntity(id)));
    }

    public RestaurantDTO.DeleteResponse delete(Long id) {
        return convertToDeleteResponseDTO(deleteRestaurantFromRequest(findRestaurantById(id)));
    }

    private List<Restaurant> readRestaurantFromRequest() {
        return this.restaurantRepository.findAllByIsActive(true);
    }

    private RestaurantDTO.Response convertToResponseDTO(Restaurant restaurant) {
        RestaurantDTO.Response response = new RestaurantDTO.Response();
        BeanUtils.copyProperties(restaurant, response);
        return response;
    }

    private Restaurant findRestaurantById(Long id) {
        return this.restaurantRepository.findById(id).get();
    }

    private Restaurant deleteRestaurantFromRequest(Restaurant restaurant) {
        return restaurantRepository.save(Restaurant.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .isActive(false)
                .build());
    }

    private RestaurantDTO.DeleteResponse convertToDeleteResponseDTO(Restaurant restaurant) {
        return RestaurantDTO.DeleteResponse.builder()
                .id(restaurant.getId())
                .build();
    }

}
