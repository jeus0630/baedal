package com.example.baemin.domain.food;

import com.example.baemin.domain.category.Category;
import com.example.baemin.domain.category.CategoryRepository;
import com.example.baemin.domain.restaurant.Restaurant;
import com.example.baemin.domain.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
        return convertToResponseDTO(foodRepository.save(req.toEntity(findCategoryById(req.getCategoryId()), findRestaurantById(req.getRestaurantId()))));
    }

    public List<FoodDto.Response> read() {
        return foodRepository.findAllByIsActive(true).stream()
                .map(food -> convertToResponseDTO(food))
                .collect(Collectors.toList());
    }

    public FoodDto.Response read(Long id) {
        return convertToResponseDTO(findFoodFromRequest(id));
    }

    public FoodDto.Response update(Long id, FoodDto.Request req) {
        return convertToResponseDTO(req.toEntity(id, findCategoryById(req.getCategoryId()), findRestaurantById(req.getRestaurantId())));
    }

    public FoodDto.DeleteResponse delete(Long id) {
        return convertToDeleteResponseDTO(
                deleteFoodFromRequest(findFoodFromRequest(id))
        );
    }

    private Food findFoodFromRequest(Long id) {
        return foodRepository.findById(id).get();
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    private Restaurant findRestaurantById(Long id) {
        return restaurantRepository.findById(id).get();
    }

    private Food deleteFoodFromRequest(Food food) {
        food.setIsActive(false);
        return foodRepository.save(food);
    }

    private FoodDto.Response convertToResponseDTO(Food food) {
        FoodDto.Response response = new FoodDto.Response();
        BeanUtils.copyProperties(food, response);
        response.setCategoryId(food.getCategory().getId());
        response.setRestaurantId(food.getRestaurant().getId());
        return response;
    }

    private FoodDto.DeleteResponse convertToDeleteResponseDTO(Food food) {
        return FoodDto.DeleteResponse.builder()
                .id(food.getId())
                .build();
    }
}
