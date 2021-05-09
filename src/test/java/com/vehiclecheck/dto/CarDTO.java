package com.vehiclecheck.dto;

public class CarDTO {
    private String registration;
    private String make;
    private String model;
    private String color;
    private String year;

    public CarDTO(String registration,
                  String make,
                  String model,
                  String color,
                  String year) {
        this.registration = registration;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public String getRegistration() {
        return registration;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Car [registration=" + registration + ", make=" + make
                + ", model=" + model + ", color=" + color
                + ", year=" + year + "]";
    }
}
