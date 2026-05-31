package com.example.courierservice.controller;

import com.example.courierservice.dto.CourierResponse;
import com.example.courierservice.dto.CreateCourierRequest;
import com.example.courierservice.dto.UpdateCourierStatusRequest;
import com.example.courierservice.service.CourierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/couriers")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourierResponse createCourier(@Valid @RequestBody CreateCourierRequest request) {
        return courierService.createCourier(request);
    }

    @GetMapping
    public List<CourierResponse> getAllCouriers() {
        return courierService.getAllCouriers();
    }

    @GetMapping("/available")
    public CourierResponse getAvailableCourier() {
        return courierService.getAvailableCourier();
    }

    @GetMapping("/{id}")
    public CourierResponse getCourierById(@PathVariable Long id) {
        return courierService.getCourierById(id);
    }

    @PatchMapping("/{id}/status")
    public CourierResponse updateCourierStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCourierStatusRequest request
    ) {
        return courierService.updateCourierStatus(id, request);
    }
}