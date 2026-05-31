package com.example.courierservice.service;

import com.example.courierservice.dto.CourierResponse;
import com.example.courierservice.dto.CreateCourierRequest;
import com.example.courierservice.dto.UpdateCourierStatusRequest;
import com.example.courierservice.entity.CourierEntity;
import com.example.courierservice.entity.CourierStatus;
import com.example.courierservice.exception.CourierNotFoundException;
import com.example.courierservice.exception.InvalidCourierStatusException;
import com.example.courierservice.exception.NoAvailableCourierException;
import com.example.courierservice.mapper.CourierMapper;
import com.example.courierservice.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;
    private final CourierMapper courierMapper;

    public CourierResponse createCourier(CreateCourierRequest request) {
        CourierEntity courier = courierMapper.toEntity(request);
        CourierEntity savedCourier = courierRepository.save(courier);

        return courierMapper.toResponse(savedCourier);
    }

    public List<CourierResponse> getAllCouriers() {
        return courierRepository.findAll()
                .stream()
                .map(courierMapper::toResponse)
                .toList();
    }

    public CourierResponse getCourierById(Long id) {
        CourierEntity courier = findCourierById(id);

        return courierMapper.toResponse(courier);
    }

    public CourierResponse getAvailableCourier() {
        CourierEntity courier = courierRepository.findFirstByStatus(CourierStatus.AVAILABLE)
                .orElseThrow(() -> new NoAvailableCourierException("No available courier found"));

        return courierMapper.toResponse(courier);
    }

    public CourierResponse updateCourierStatus(Long id, UpdateCourierStatusRequest request) {
        CourierEntity courier = findCourierById(id);

        validateStatusTransition(courier.getStatus(), request.getStatus());

        courier.setStatus(request.getStatus());

        CourierEntity updatedCourier = courierRepository.save(courier);

        return courierMapper.toResponse(updatedCourier);
    }

    private CourierEntity findCourierById(Long id) {
        return courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException("Courier not found with id: " + id));
    }

    private void validateStatusTransition(CourierStatus currentStatus, CourierStatus nextStatus) {
        if (currentStatus == nextStatus) {
            throw new InvalidCourierStatusException("Courier is already " + nextStatus);
        }
    }

    public void markCourierAsOnDelivery(Long courierId) {
        CourierEntity courier = findCourierById(courierId);

        validateStatusTransition(courier.getStatus(), CourierStatus.ON_DELIVERY);

        courier.setStatus(CourierStatus.ON_DELIVERY);
        courierRepository.save(courier);
    }

    public void markCourierAsAvailable(Long courierId) {
        CourierEntity courier = findCourierById(courierId);

        validateStatusTransition(courier.getStatus(), CourierStatus.AVAILABLE);

        courier.setStatus(CourierStatus.AVAILABLE);
        courierRepository.save(courier);
    }
}