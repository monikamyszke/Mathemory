package com.example.monikam.mathemory;

import com.example.monikam.mathemory.CategoryClass;

/**
 * Created by MonikaM on 2017-11-24.
 */

class Fractions extends CategoryClass {

    private String instruction;

    @Override
    public String getInstruction(int curr_level) {

        if (curr_level == 3 || curr_level == 6 || curr_level == 9) {
            instruction = "Wybierz ułamki niewłaściwe:";
        }
        else {
            instruction = "Wybierz ułamki właściwe:";
        }

        return instruction;
    }

    @Override
    public String[] generateNumbers(int fields_num, int curr_level) {
        return new String[fields_num];
    }

    @Override
    public boolean check(int sel_field) {
        return false;
    }
}
