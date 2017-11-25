package com.example.monikam.mathemory;

import com.example.monikam.mathemory.CategoryClass;

import java.util.Random;

/**
 * Created by MonikaM on 2017-11-24.
 */

class Parity extends CategoryClass {
    @Override
    public int[] generateNumbers(int fields_num, int curr_level) {

        int[] numbers = new int[fields_num];
        Random gen = new Random();

        for(int i = 0; i < fields_num; i++){
            numbers[i] = gen.nextInt(10);
        }

        return numbers;
    }

    @Override
    public boolean check(int sel_field) {

        return false;
    }
}
