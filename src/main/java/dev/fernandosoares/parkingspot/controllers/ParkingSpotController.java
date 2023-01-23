package dev.fernandosoares.parkingspot.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fernandosoares.parkingspot.dtos.ParkingSpotDto;
import dev.fernandosoares.parkingspot.models.ParkingSpotModel;
import dev.fernandosoares.parkingspot.services.ParkingSpotService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {

        if (parkingSpotService.existsByCarLicensePlate(parkingSpotDto.getCarLicensePlate())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate is alread in use.");
        }
        if (parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: ParkingSpot is alread in use.");
        }
        if (parkingSpotService.existsByApartmentAndApartmentTower(parkingSpotDto.getApartment(),
                parkingSpotDto.getApartmentTower())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict: Parking Spot is alread in use by another apartment.");
        }

        ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }

    @GetMapping
    public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(
            @PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {

        Optional<ParkingSpotModel> parkingSpotOptionalModel = parkingSpotService.findById(id);
        if (!parkingSpotOptionalModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotOptionalModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) {

        Optional<ParkingSpotModel> parkingSpotOptionalModel = parkingSpotService.findById(id);
        if (!parkingSpotOptionalModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");
        }
        parkingSpotService.delete(parkingSpotOptionalModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted!");

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid ParkingSpotDto parkingSpotDto) {

        Optional<ParkingSpotModel> parkintSpotModelOptional = parkingSpotService.findById(id);
        if (!parkintSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Update failed! Parking spot not found!");
        }

        // ParkingSpotModel parkingSpotModel = parkintSpotModelOptional.get();

        // parkingSpotModel.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
        // parkingSpotModel.setCarLicensePlate(parkingSpotDto.getCarLicensePlate());
        // parkingSpotModel.setCarBrand(parkingSpotDto.getCarBrand());
        // parkingSpotModel.setCarModel(parkingSpotDto.getCarModel());
        // parkingSpotModel.setCarColor(parkingSpotDto.getCarColor());
        // parkingSpotModel.setRegistrationName(parkingSpotDto.getRegistrationName());
        // parkingSpotModel.setApartment(parkingSpotDto.getApartment());
        // parkingSpotModel.setApartmentTower(parkingSpotDto.getApartmentTower());

        ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setId(parkintSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkintSpotModelOptional.get().getRegistrationDate());

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }

}
