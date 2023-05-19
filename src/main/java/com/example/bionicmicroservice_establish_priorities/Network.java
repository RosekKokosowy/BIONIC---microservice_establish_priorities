package com.example.bionicmicroservice_establish_priorities;

import java.time.Year;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class Network {

    private CarParameters m_ArbitraryModel;
    private ParametersWeight m_ArbitraryWeights;
    private ParametersWeight m_PreviousArbitraryWeights;

    private ParametersWeight m_CurrentCarToParams;

    private double min[] = new double[4];
    private double max[] = new double[4];

    private int m_Iteration;
    private int counter_Fuels[];
    private int counter_Gearbox[];

    private double HEAT = 0.2;
    private double ACTIVATION_BOUNDARY = 0.2;

    public ParametersWeight output;


    public Network()
    {
        output = new ParametersWeight();

        SetBoundaries();
        InitModel();
        Normalize();
    }

    public void Train( List< CarParameters> cars)
    {
        Predict();


        for( CarParameters c : cars)
        {
            ParametersWeight w = DrawNormals( c);
            Step( w);
        }

        output = m_ArbitraryWeights;
    }

    private void InitModel()
    {
        m_ArbitraryModel = new CarParameters(
                0,
            //yearOfManufacture
                (min[0] + max[0]) / 2.0,

            //mileage
                (min[1] + max[1]) / 2.0,

            //price
                (min[2] + max[2]) / 2.0,

            //horsePower
                (min[3] + max[3]) / 2.0,

                "petrol",

                "automatic");

        m_Iteration = 0;
    }

    private void Step( ParametersWeight weights)
    {
        if( Activation(m_ArbitraryWeights.getYearOfManufacture(), weights.getYearOfManufacture()))
        {
            m_ArbitraryWeights.setYearOfManufacture(m_PreviousArbitraryWeights.getYearOfManufacture() + (m_PreviousArbitraryWeights.getYearOfManufacture() - weights.getYearOfManufacture()) * HEAT);
        }

        if( Activation(m_ArbitraryWeights.getMileage(), weights.getMileage()))
        {
            m_ArbitraryWeights.setMileage(m_PreviousArbitraryWeights.getMileage()  + (m_PreviousArbitraryWeights.getMileage() - weights.getMileage()) * HEAT);
        }

        if( Activation(m_ArbitraryWeights.getPrice(), weights.getPrice()))
        {
            m_ArbitraryWeights.setPrice(m_PreviousArbitraryWeights.getPrice()  + (m_PreviousArbitraryWeights.getPrice() - weights.getPrice()) * HEAT);
        }

        if( Activation(m_ArbitraryWeights.getHorsePower(), weights.getHorsePower()))
        {
            m_ArbitraryWeights.setHorsePower(m_PreviousArbitraryWeights.getHorsePower() + (m_PreviousArbitraryWeights.getHorsePower() - weights.getHorsePower()) * HEAT);
        }
    }

    private void Predict()
    {
        Random rand = new Random();
        m_PreviousArbitraryWeights = m_ArbitraryWeights;

        double value = rand.nextDouble(m_ArbitraryWeights.getYearOfManufacture());
        m_ArbitraryWeights.setYearOfManufacture(m_ArbitraryWeights.getYearOfManufacture() + (value - m_ArbitraryWeights.getYearOfManufacture()/2) * HEAT);

        value = rand.nextDouble(m_ArbitraryWeights.getMileage());
        m_ArbitraryWeights.setMileage(m_ArbitraryWeights.getMileage() + (value - m_ArbitraryWeights.getMileage()/2) * HEAT);

        value = rand.nextDouble(m_ArbitraryWeights.getPrice());
        m_ArbitraryWeights.setPrice(m_ArbitraryWeights.getPrice() + (value - m_ArbitraryWeights.getPrice()/2) * HEAT);

        value = rand.nextDouble(m_ArbitraryWeights.getHorsePower());
        m_ArbitraryWeights.setHorsePower(m_ArbitraryWeights.getHorsePower() + (value - m_ArbitraryWeights.getHorsePower()/2) * HEAT);
    }

    private void Normalize()
    {
        m_ArbitraryWeights.setYearOfManufacture((m_ArbitraryModel.getYearOfManufacture() - min[0])/(max[0] - min[0]));

        m_ArbitraryWeights.setMileage((m_ArbitraryModel.getMileage() - min[1])/(max[1] - min[1]));

        m_ArbitraryWeights.setPrice((m_ArbitraryModel.getPrice() - min[2])/(max[2] - min[2]));

        m_ArbitraryWeights.setHorsePower((m_ArbitraryModel.getHorsePower() - min[3])/(max[3] - min[3]));
    }

    private ParametersWeight DrawNormals(CarParameters car)
    {
        ParametersWeight out = new ParametersWeight();

        out.setYearOfManufacture((car.getYearOfManufacture() - min[0])/(max[0] - min[0]));

        out.setMileage((car.getMileage() - min[1])/(max[1] - min[1]));

        out.setPrice((car.getPrice() - min[2])/(max[2] - min[2]));

        out.setHorsePower((car.getHorsePower() - min[3])/(max[3] - min[3]));

        return out;
    }

    private void SetBoundaries()
    {
        //yearOfManufacture
        min[0] = 1950;
        max[0] = Year.now().getValue();

        //mileage
        min[1] = 0;
        max[1] = 1000000;

        //price
        min[2] = 0;
        max[2] = 5000000;

        //horsePower
        min[3] = 0;
        max[3] = 1500;
    }

    private boolean Activation(double a, double b)
    {
        return abs(a - b) >= ACTIVATION_BOUNDARY;
    }
}
