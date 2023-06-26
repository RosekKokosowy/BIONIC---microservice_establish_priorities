package com.example.bionicmicroservice_establish_priorities.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarParameters {
    private int id;
    private double yearOfManufacture;
    private double mileage;
    private double price;
    private double horsePower;
    private String typeOfFuel;
    private String gearBox;
}
