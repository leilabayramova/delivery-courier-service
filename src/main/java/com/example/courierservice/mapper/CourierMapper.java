package com.example.courierservice.mapper;

import com.example.courierservice.dto.CourierResponse;
import com.example.courierservice.dto.CreateCourierRequest;
import com.example.courierservice.entity.CourierEntity;
import com.example.courierservice.entity.CourierStatus;
import org.springframework.stereotype.Component;

@Component
public class CourierMapper {

    public CourierEntity toEntity(CreateCourierRequest request) {
        CourierEntity courier = new CourierEntity();
        courier.setFullName(request.getFullName());
        courier.setPhoneNumber(request.getPhoneNumber());
        courier.setStatus(CourierStatus.AVAILABLE);

        return courier;
    }

    public CourierResponse toResponse(CourierEntity courier) {
        CourierResponse response = new CourierResponse();
        response.setId(courier.getId());
        response.setFullName(courier.getFullName());
        response.setPhoneNumber(courier.getPhoneNumber());
        response.setStatus(courier.getStatus());

        return response;
    }
}