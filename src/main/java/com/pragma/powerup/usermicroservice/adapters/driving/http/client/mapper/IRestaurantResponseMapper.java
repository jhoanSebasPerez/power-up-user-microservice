package com.pragma.powerup.usermicroservice.adapters.driving.http.client.mapper;

import com.pragma.powerup.usermicroservice.adapters.driving.http.client.dto.RestaurantResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {

    Restaurant toEntity(RestaurantResponseDto responseDto);
}
