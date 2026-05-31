package com.example.courierservice.dto;

import com.example.courierservice.entity.CourierStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourierResponse {
    private Long id;

    private String fullName;

    private String phoneNumber;

    private CourierStatus status;
}
