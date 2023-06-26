package com.example.bionicmicroservice_establish_priorities.web;

import com.example.bionicmicroservice_establish_priorities.data.CarParameters;
import com.example.bionicmicroservice_establish_priorities.dtos.QuizDto;
import com.example.bionicmicroservice_establish_priorities.service.NetworkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/quiz")
@RestController
public class NetworkController {

    private final NetworkService networkService;

    @Autowired
    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @PostMapping("/priorities")
    public ResponseEntity<?> accessPriorities(@RequestBody QuizDto quizDto){
        try{
            networkService.initNetwork();
            networkService.Train(mapQuizDtoToCarParameters(quizDto));
            return ResponseEntity.ok(networkService.output);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error while mapping!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    private List<CarParameters> mapQuizDtoToCarParameters(QuizDto quizDto) {
        List<CarParameters> carParametersList = new ArrayList<>();
        List<Integer> choices = quizDto.getChoices();

        for (Object car : quizDto.getCars()) {
            // Verify the car object can be casted to CarParameters
            if(car instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) car;

                try {
                    Integer id = Integer.parseInt(map.get("id").toString());

                    // Check if this car's id is in the choices list
                    if(choices.contains(id)) {
                        CarParameters carParameters = new CarParameters();
                        carParameters.setId(id);
                        carParameters.setYearOfManufacture(Double.parseDouble(map.get("yearOfManufacture").toString().replace(" ", "")));
                        carParameters.setMileage(Double.parseDouble(map.get("mileage").toString().replace(" ", "")));
                        carParameters.setPrice(Double.parseDouble(map.get("price").toString().replaceAll("[^\\d.]", "")));
                        carParameters.setHorsePower(Double.parseDouble(map.get("horsePower").toString().replace(" ", "")));
                        carParameters.setTypeOfFuel(map.get("typeOfFuel").toString());
                        carParameters.setGearBox(map.get("gearBox").toString());

                        carParametersList.add(carParameters);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format in car object", e);
                }
            } else {
                throw new IllegalArgumentException("Invalid car object");
            }
        }
        return carParametersList;
    }
}
