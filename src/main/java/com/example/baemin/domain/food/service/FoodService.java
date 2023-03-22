package com.example.baemin.domain.food.service;

import com.example.baemin.domain.category.repository.CategoryRepository;
import com.example.baemin.domain.entity.Category;
import com.example.baemin.domain.entity.Food;
import com.example.baemin.domain.entity.Restaurant;
import com.example.baemin.domain.food.dto.FoodDto;
import com.example.baemin.domain.food.repository.FoodRepository;
import com.example.baemin.domain.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;

    public FoodDto.FoodCreateResponse create(FoodDto.FoodCreateRequest req) {
        return convertToFoodCreateResponseDTO(
                this.foodRepository.save(createFoodFromRequest(req))
        );
    }

    public List<FoodDto.FoodReadResponse> read() {
        return this.foodRepository.findAllByIsActive(true).stream()
                .map(food -> convertToFoodReadResponseDTO(food))
                .collect(Collectors.toList());
    }

    public FoodDto.FoodUpdateResponse update(Long id, FoodDto.FoodUpdateRequest req) {
        return convertToFoodUpdateResponseDTO(
                updateFoodFromRequest(findFoodFromRequest(id), req)
        );
    }

    public FoodDto.FoodDeleteResponse delete(Long id) {
        return convertToFoodDeleteResponseDTO(
                deleteFoodFromRequest(findFoodFromRequest(id))
        );
    }

    private Food createFoodFromRequest(FoodDto.FoodCreateRequest req) {
        return Food.builder()
                .name(req.getName())
                .price(req.getPrice())
                .category(findCategoryById(req.getCategoryId()))
                .restaurant(findRestaurantById(req.getRestaurantId()))
                .build();
    }

    private Food findFoodFromRequest(Long id) {
        return foodRepository.findById(id).get();
    }

    private Category findCategoryById(Long id) {
        return this.categoryRepository.findById(id).get();
    }

    private Restaurant findRestaurantById(Long id) {
        return this.restaurantRepository.findById(id).get();
    }

    private Food updateFoodFromRequest(Food food, FoodDto.FoodUpdateRequest req) {
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setCategory(findCategoryById(req.getCategoryId()));
        food.setRestaurant(findRestaurantById(req.getRestaurantId()));

        return foodRepository.save(food);
    }

    private Food deleteFoodFromRequest(Food food) {
        food.setIsActive(false);
        return foodRepository.save(food);
    }

    private FoodDto.FoodCreateResponse convertToFoodCreateResponseDTO(Food food) {
        return FoodDto.FoodCreateResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .price(food.getPrice())
                .categoryId(food.getCategory().getId())
                .restaurantId(food.getRestaurant().getId())
                .build();
    }

    private FoodDto.FoodReadResponse convertToFoodReadResponseDTO(Food food) {
        return FoodDto.FoodReadResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .price(food.getPrice())
                .categoryId(food.getCategory().getId())
                .restaurantId(food.getRestaurant().getId())
                .build();
    }

    private FoodDto.FoodUpdateResponse convertToFoodUpdateResponseDTO(Food food) {
        return FoodDto.FoodUpdateResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .price(food.getPrice())
                .categoryId(food.getCategory().getId())
                .restaurantId(food.getRestaurant().getId())
                .build();
    }

    private FoodDto.FoodDeleteResponse convertToFoodDeleteResponseDTO(Food food) {
        return FoodDto.FoodDeleteResponse.builder()
                .id(food.getId())
                .build();
    }
}
