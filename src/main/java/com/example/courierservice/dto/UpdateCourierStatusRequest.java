package com.example.courierservice.dto;

import com.example.courierservice.entity.CourierStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourierStatusRequest {
    @NotNull(message = "Courier status is required")
    private CourierStatus status;
}
