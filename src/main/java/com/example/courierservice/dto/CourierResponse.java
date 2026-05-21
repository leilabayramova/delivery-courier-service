package com.example.courierservice.dto;

import com.example.courierservice.entity.CourierStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierResponse {
    private Long id;

    private String fullName;

    private String phoneNumber;

    private CourierStatus status;
}
