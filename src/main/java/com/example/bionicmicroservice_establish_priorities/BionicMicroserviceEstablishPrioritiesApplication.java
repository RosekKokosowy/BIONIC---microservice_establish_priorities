package com.example.bionicmicroservice_establish_priorities;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BionicMicroserviceEstablishPrioritiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BionicMicroserviceEstablishPrioritiesApplication.class, args);

        //--INSTRUKCJA OBSLUGI--
        // Network n = new Network();
        // n.Train(List wybranych samochodow);
        // ParametersWeight output = n.output;
        // Kluczowe jest by lista podawana jako argument funkcji Train() była jedynie listą samochodów preferowanych.
        // Co za tym idzie sieć zupełnie nie jest zainteresowana samochodami, których uzytkownik nie zaznaczył.
    }

}
