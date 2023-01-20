package dev.fernandosoares.parkingspot.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String parkingSpotNumber;

    @Column(nullable = false, unique = true, length = 7)
    private String carLicensePlate;

    @Column(nullable = false, length = 70)
    private String carBrand;

    @Column(nullable = false, length = 70)
    private String carModel;

    @Column(nullable = false, length = 70)
    private String carColor;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false, length = 30)
    private String registrationName;

    @Column(nullable = false, length = 30)
    private String apartment;

    @Column(nullable = false, length = 30)
    private String apartmentTower;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationName() {
        return registrationName;
    }

    public void setRegistrationName(String registrationName) {
        this.registrationName = registrationName;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getApartmentTower() {
        return apartmentTower;
    }

    public void setApartmentTower(String apartmentTower) {
        this.apartmentTower = apartmentTower;
    }

    @Override
    public String toString() {
        return "ParkingSpotModel [id=" + id + ", parkingSpotNumber=" + parkingSpotNumber + ", carLicensePlate="
                + carLicensePlate + ", carBrand=" + carBrand + ", carModel=" + carModel + ", carColor=" + carColor
                + ", registrationDate=" + registrationDate + ", registrationName=" + registrationName + ", apartment="
                + apartment + ", apartmentTower=" + apartmentTower + "]";
    }

}
