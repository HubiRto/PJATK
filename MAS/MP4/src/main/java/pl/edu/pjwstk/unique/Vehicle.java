package pl.edu.pjwstk.unique;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vehicle {
    private String brand;
    private String model;
    private String registrationNumber;

    private static final List<Vehicle> extent = new ArrayList<>();

    public Vehicle(String brand, String model, String registrationNumber) {
        setBrand(brand);
        setModel(model);
        setRegistrationNumber(registrationNumber);
        extent.add(this);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be empty");
        }
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model cannot be empty");
        }
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException("Registration number cannot be empty");
        }

        if (extentContains(registrationNumber)) {
            throw new IllegalArgumentException("Registration number already exists");
        }

        this.registrationNumber = registrationNumber;
    }

    public static List<Vehicle> getExtent() {
        return Collections.unmodifiableList(extent);
    }

    private boolean extentContains(String registrationNumber) {
        return extent.stream().anyMatch(v -> v.getRegistrationNumber() != null && v.getRegistrationNumber().equals(registrationNumber));
    }

    @Override
    public String toString() {
        return "Vehicle(brand: %s, model: %s, registrationNumber: %s)".formatted(brand, model, registrationNumber);
    }
}
