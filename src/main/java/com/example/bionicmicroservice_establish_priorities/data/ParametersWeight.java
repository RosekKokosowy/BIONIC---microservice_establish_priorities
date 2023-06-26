package com.example.bionicmicroservice_establish_priorities.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParametersWeight implements Serializable {

    private Long id;
    private double yearOfManufacture;
    private double mileage;
    private double price;
    private double horsePower;
    private double typeOfFuel;
    private double gearBox;
}
