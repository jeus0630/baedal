package com.example.baemin.domain.food;

import com.example.baemin.domain.category.Category;
import com.example.baemin.domain.category.CategoryRepository;
import com.example.baemin.domain.restaurant.Restaurant;
import com.example.baemin.domain.restaurant.RestaurantRepository;
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

    public FoodDto.Response create(FoodDto.Request req) {
        return convertToResponseDTO(
                this.foodRepository.save(createFoodFromRequest(req))
        );
    }

    public List<FoodDto.Response> read() {
        return this.foodRepository.findAllByIsActive(true).stream()
                .map(food -> convertToResponseDTO(food))
                .collect(Collectors.toList());
    }

    public FoodDto.Response read(Long id) {
        return convertToResponseDTO(findFoodFromRequest(id));
    }

    public FoodDto.Response update(Long id, FoodDto.Request req) {
        return convertToResponseDTO(
                updateFoodFromRequest(findFoodFromRequest(id), req)
        );
    }

    public FoodDto.DeleteResponse delete(Long id) {
        return convertToDeleteResponseDTO(
                deleteFoodFromRequest(findFoodFromRequest(id))
        );
    }

    private Food createFoodFromRequest(FoodDto.Request req) {
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

    private Food updateFoodFromRequest(Food food, FoodDto.Request req) {
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

    private FoodDto.Response convertToResponseDTO(Food food) {
        return FoodDto.Response.builder()
                .id(food.getId())
                .name(food.getName())
                .price(food.getPrice())
                .categoryId(food.getCategory().getId())
                .restaurantId(food.getRestaurant().getId())
                .build();
    }

    private FoodDto.DeleteResponse convertToDeleteResponseDTO(Food food) {
        return FoodDto.DeleteResponse.builder()
                .id(food.getId())
                .build();
    }
}
