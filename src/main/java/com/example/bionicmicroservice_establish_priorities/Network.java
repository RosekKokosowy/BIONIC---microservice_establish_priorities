////package com.example.bionicmicroservice_establish_priorities;
////
////import com.example.bionicmicroservice_establish_priorities.data.CarParameters;
////
////import java.time.Year;
////import java.util.List;
////import java.util.Random;
////
////import static java.lang.Math.abs;
////
////public class Network {
////
////    private CarParameters m_ArbitraryModel;
////    private ParametersWeight m_ArbitraryWeights;
////    private ParametersWeight m_PreviousArbitraryWeights;
////
////    private ParametersWeight m_CurrentCarToParams;
////
////    private double min[] = new double[4];
////    private double max[] = new double[4];
////
////    private int m_Iteration;
////    private int counter_Fuels[];
////    private int counter_Gearbox[];
////
////    private double HEAT = 0.1;
////    private double ACTIVATION_BOUNDARY = 0.1;
////
////    public ParametersWeight output;
////
////
////    public Network()
////    {
////        output = new ParametersWeight();
////
////        SetBoundaries();
////        InitModel();
////        Normalize();
////    }
////
////    public void Train( List< CarParameters> cars)
////    {
////        for( CarParameters c : cars)
////        {
////            ParametersWeight w = DrawNormals( c);
////            Predict();
//            Step( w);
//            m_ActualAcuraccy.add( JudgementAcuraccy( m_ArbitraryWeights, w));
////            DebugDisp();
////            m_Iteration++;
////        }
////
////        output = m_ArbitraryWeights;
////    }
////
////    private void InitModel()
////    {
////        m_ArbitraryModel = new CarParameters(
////                0,
////            //yearOfManufacture
////                (min[0] + max[0]) / 2.0,
////
////            //mileage
////                (min[1] + max[1]) / 2.0,
////
////            //price
////                (min[2] + max[2]) / 2.0,
////
////            //horsePower
////                (min[3] + max[3]) / 2.0,
////
////                "petrol",
////
////                "automatic");
////
////        m_ArbitraryWeights = DrawNormals(m_ArbitraryModel);
////
////        m_Iteration = 0;
////    }
////
//    private double JudgementAcuraccy( ParametersWeight model, ParametersWeight input)
//    {
//        double res = 0;
//        res += abs( model.getHorsePower() - input.getHorsePower());
//        res += abs( model.getMileage() - input.getMileage());
//        res += abs( model.getPrice() - input.getPrice());
//        res += abs( model.getYearOfManufacture() - input.getYearOfManufacture());
//        return res;
//    }
//
//    private void Step( ParametersWeight weights)
////    {
//        double ret = JudgementAcuraccy( m_ArbitraryWeights, weights);
////        double res;
////        if( Activation(m_ArbitraryWeights.getYearOfManufacture(), weights.getYearOfManufacture())) {
////            res = m_PreviousArbitraryWeights.getYearOfManufacture() + (weights.getYearOfManufacture() - m_PreviousArbitraryWeights.getYearOfManufacture()) * HEAT;
////            if( res > 1)    res = 1;
////            if( res < 0)    res = 0;
////            m_ArbitraryWeights.setYearOfManufacture(res);
////        }
////
////        if( Activation(m_ArbitraryWeights.getMileage(), weights.getMileage()))
////        {
////            res = m_PreviousArbitraryWeights.getMileage()  + (weights.getMileage() - m_PreviousArbitraryWeights.getMileage()) * HEAT;
////            if( res > 1)    res = 1;
////            if( res < 0)    res = 0;
////            m_ArbitraryWeights.setMileage(res);
////        }
////
////        if( Activation(m_ArbitraryWeights.getPrice(), weights.getPrice()))
////        {
////            res = m_PreviousArbitraryWeights.getPrice()  + (weights.getPrice() - m_PreviousArbitraryWeights.getPrice()) * HEAT;
////            if( res > 1)    res = 1;
////            if( res < 0)    res = 0;
////            m_ArbitraryWeights.setPrice(res);
////        }
////
////        if( Activation(m_ArbitraryWeights.getHorsePower(), weights.getHorsePower()))
////        {
////            res = m_PreviousArbitraryWeights.getHorsePower() + (weights.getHorsePower() - m_PreviousArbitraryWeights.getHorsePower()) * HEAT;
////            if( res > 1)    res = 1;
////            if( res < 0)    res = 0;
////            m_ArbitraryWeights.setHorsePower(res);
////        }
//        return ret;
////    }
////
////    private double GetRandValueSafe(double internal)
////    {
////        Random rand = new Random();
////        return internal > 0 ? rand.nextDouble(internal) : rand.nextDouble(HEAT);
////    }
////
////    private void Predict()
////    {
////
////        double res;
////        double value;
////        m_PreviousArbitraryWeights = m_ArbitraryWeights;
////
////        value = GetRandValueSafe( m_ArbitraryWeights.getYearOfManufacture());
////        res = m_ArbitraryWeights.getYearOfManufacture() + (value - GetRandValueSafe(value)) * HEAT;
////        if( res < 0)    res = 0;
////        if( res > 1)    res = 1;
////        m_ArbitraryWeights.setYearOfManufacture(res);
////
////        value = GetRandValueSafe( m_ArbitraryWeights.getMileage());
////        res = m_ArbitraryWeights.getMileage() + (value - GetRandValueSafe(value)) * HEAT;
////        if( res < 0)    res = 0;
////        if( res > 1)    res = 1;
////        m_ArbitraryWeights.setMileage(res);
////
////        value = GetRandValueSafe(m_ArbitraryWeights.getPrice());
////        res = m_ArbitraryWeights.getPrice() + (value - GetRandValueSafe(value)) * HEAT;
////        if( res < 0)    res = 0;
////        if( res > 1)    res = 1;
////        m_ArbitraryWeights.setPrice(res);
////
////        value = GetRandValueSafe(m_ArbitraryWeights.getHorsePower());
////        res = m_ArbitraryWeights.getHorsePower() + (value - GetRandValueSafe(value)) * HEAT;
////        if( res < 0)    res = 0;
////        if( res > 1)    res = 1;
////        m_ArbitraryWeights.setHorsePower(res);
////    }
////
////    private void Normalize()
////    {
////        m_ArbitraryWeights.setYearOfManufacture((m_ArbitraryModel.getYearOfManufacture() - min[0])/(max[0] - min[0]));
////
////        m_ArbitraryWeights.setMileage((m_ArbitraryModel.getMileage() - min[1])/(max[1] - min[1]));
////
////        m_ArbitraryWeights.setPrice((m_ArbitraryModel.getPrice() - min[2])/(max[2] - min[2]));
////
////        m_ArbitraryWeights.setHorsePower((m_ArbitraryModel.getHorsePower() - min[3])/(max[3] - min[3]));
////    }
////
////    private ParametersWeight DrawNormals(CarParameters car)
////    {
////        ParametersWeight out = new ParametersWeight();
////
////        out.setYearOfManufacture((car.getYearOfManufacture() - min[0])/(max[0] - min[0]));
////
////        out.setMileage((car.getMileage() - min[1])/(max[1] - min[1]));
////
////        out.setPrice((car.getPrice() - min[2])/(max[2] - min[2]));
////
////        out.setHorsePower((car.getHorsePower() - min[3])/(max[3] - min[3]));
////
////        return out;
////    }
////
////    private void SetBoundaries()
////    {
////        //yearOfManufacture
////        min[0] = 1950;
////        max[0] = Year.now().getValue();
////
////        //mileage
////        min[1] = 0;
////        max[1] = 1000000;
////
////        //price
////        min[2] = 0;
////        max[2] = 5000000;
////
////        //horsePower
////        min[3] = 0;
////        max[3] = 1500;
////    }
////
////    private boolean Activation(double a, double b)
////    {
////        return abs(a - b) >= ACTIVATION_BOUNDARY;
////    }
////
////    private void DebugDisp()
////    {
////        System.out.println("Iteration: " + m_Iteration +
////                "\nYear of manufacture: " + m_ArbitraryWeights.getYearOfManufacture() +
////                "\nMileage: " + m_ArbitraryWeights.getMileage() +
////                "\nPrice: " + m_ArbitraryWeights.getPrice() +
////                "\nHorsePower: " + m_ArbitraryWeights.getHorsePower()
////        );
////    }
////}
