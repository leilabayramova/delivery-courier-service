package com.example.courierservice.repository;

import com.example.courierservice.entity.CourierEntity;
import com.example.courierservice.entity.CourierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, Long> {
    Optional<CourierEntity> findFirstByStatus(CourierStatus status);
}
