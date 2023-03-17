package com.example.baemin.domain.restaurant.service;

import com.example.baemin.domain.entity.Restaurant;
import com.example.baemin.domain.restaurant.dto.RestaurantDTO;
import com.example.baemin.domain.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantDTO.RestaurantCreateResponse create(RestaurantDTO.RestaurantCreateRequest req) {
        return convertToRestaurantCreateResponseDTO(createRestaurantFromRequest(req));
    }

    public List<RestaurantDTO.RestaurantReadResponse> read() {
        return readRestaurantFromRequest().stream().map(
                restaurant -> convertToRestaurantReadResponseDTO(restaurant)
        ).collect(Collectors.toList());
    }

    public RestaurantDTO.RestaurantUpdateResponse update(Long id, RestaurantDTO.RestaurantUpdateRequest req) {
        return convertToRestaurantUpdateResponseDTO(updateRestaurantFromRequest(id, req));
    }

    public RestaurantDTO.RestaurantDeleteResponse delete(Long id) {
        return convertToRestaurantDeleteResponseDTO(deleteRestaurantFromRequest(id));
    }

    private Restaurant createRestaurantFromRequest(RestaurantDTO.RestaurantCreateRequest req) {
        return this.restaurantRepository.save(
                Restaurant.builder()
                        .name(req.getName())
                        .location(req.getLocation())
                        .build()
        );
    }

    private RestaurantDTO.RestaurantCreateResponse convertToRestaurantCreateResponseDTO(Restaurant restaurant) {
        return RestaurantDTO.RestaurantCreateResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .build();
    }

    private List<Restaurant> readRestaurantFromRequest() {
        return this.restaurantRepository.findAllByIsActive(true);
    }

    private RestaurantDTO.RestaurantReadResponse convertToRestaurantReadResponseDTO(Restaurant restaurant) {
        return RestaurantDTO.RestaurantReadResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .build();
    }

    private Restaurant findRestaurantById(Long id) {
        return this.restaurantRepository.findById(id).get();
    }

    private Restaurant updateRestaurantFromRequest(Long id, RestaurantDTO.RestaurantUpdateRequest req) {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setName(req.getName());
        restaurant.setLocation(req.getLocation());
        return this.restaurantRepository.save(restaurant);
    }

    private RestaurantDTO.RestaurantUpdateResponse convertToRestaurantUpdateResponseDTO(Restaurant restaurant) {
        return RestaurantDTO.RestaurantUpdateResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .build();
    }

    private Restaurant deleteRestaurantFromRequest(Long id) {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setIsActive(false);
        return this.restaurantRepository.save(restaurant);
    }

    private RestaurantDTO.RestaurantDeleteResponse convertToRestaurantDeleteResponseDTO(Restaurant restaurant) {
        return RestaurantDTO.RestaurantDeleteResponse.builder()
                .id(restaurant.getId())
                .build();
    }

}
