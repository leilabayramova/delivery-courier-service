package com.example.courierservice.mapper;

import com.example.courierservice.dto.CourierResponse;
import com.example.courierservice.dto.CreateCourierRequest;
import com.example.courierservice.entity.CourierEntity;
import com.example.courierservice.entity.CourierStatus;
import org.springframework.stereotype.Component;

@Component
public class CourierMapper {

    public CourierEntity toEntity(CreateCourierRequest request) {
        return CourierEntity.builder()
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .status(CourierStatus.AVAILABLE)
                .build();
    }

    public CourierResponse toResponse(CourierEntity courier) {
        return CourierResponse.builder()
                .id(courier.getId())
                .fullName(courier.getFullName())
                .phoneNumber(courier.getPhoneNumber())
                .status(courier.getStatus())
                .build();
    }
}