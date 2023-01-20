package dev.fernandosoares.parkingspot.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fernandosoares.parkingspot.models.ParkingSpotModel;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {

    boolean existsByCarLicensePlate(String licensePlate);

    boolean existsByParkingSpotNumber(String parkingSpotNumber);

    boolean existsByApartmentAndApartmentTower(String apartment, String apartmentTower);
}
