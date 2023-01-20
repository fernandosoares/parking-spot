package dev.fernandosoares.parkingspot.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ParkingSpotDto {

    @NotBlank
    private String parkingSpotNumber;
    @NotBlank
    @Size(max = 7)
    private String carLicensePlate;
    @NotBlank
    private String carBrand;
    @NotBlank
    private String carModel;
    @NotBlank
    private String carColor;
    @NotBlank
    private String registrationName;
    @NotBlank
    private String apartment;
    @NotBlank
    private String apartmentTower;

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
        return "ParkingSpotDto [parkingSpotNumber=" + parkingSpotNumber + ", carLicensePlate=" + carLicensePlate
                + ", carBrand=" + carBrand + ", carModel=" + carModel + ", carColor=" + carColor + ", registrationName="
                + registrationName + ", apartment=" + apartment + ", apartmentTower=" + apartmentTower + "]";
    }
}
