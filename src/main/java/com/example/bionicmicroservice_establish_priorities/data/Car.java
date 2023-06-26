package com.example.bionicmicroservice_establish_priorities.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {

    private Long id;
    private String name;
    private String img;
    private String yearOfManufacture;
    private String mileage;
    private String price;
    private String horsePower;
    private String typeOfFuel;
    private String gearBox;


}
