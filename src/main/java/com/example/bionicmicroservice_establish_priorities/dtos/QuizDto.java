package com.example.bionicmicroservice_establish_priorities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private ArrayList<Object> cars;
    private ArrayList<Integer> choices;
    private int showed = 0;
    public static final int MAX_SIZE = 36;
}