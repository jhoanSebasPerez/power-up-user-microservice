package com.pragma.powerup.usermicroservice.adapters.driving.http.client;

import com.pragma.powerup.usermicroservice.adapters.driving.http.client.dto.RestaurantResponseDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "FOOD-COURT-SERVICE")
@Headers("Authorization: {token}")
public interface IRestaurantClient {

    @RequestLine("GET /food-court-service/restaurant/{restaurantId}")
    RestaurantResponseDto getRestaurant(
            @Param("token")String token,
            @Param("restaurantId")Long restaurantId);
}
