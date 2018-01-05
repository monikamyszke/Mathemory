package com.example.monikam.mathemory;

import java.util.ArrayList;
import java.util.List;

class Fractions extends CategoryClass {

    private String instruction;
    private List<IntegerPair> fractions;
    private int counter;

    @Override
    public String getInstruction(int curr_level) {

        if (curr_level == 3 || curr_level == 6 || curr_level == 9) {
            instruction = "ułamki niewłaściwe";
        }
        else {
            instruction = "ułamki właściwe";
        }

        return instruction;
    }

    @Override
    public String[] generateNumbers(int fields_num, int curr_level) {

        String[] sGenerated = new String[fields_num];
        fractions = new ArrayList<>();

        do {
            fractions.clear();
            counter = 0;

            for (int i = 0; i < fields_num; i++) {

                IntegerPair fraction = new IntegerPair();

                do {
                    int multiplier = 10;
                    int multiplier2 = 7;
                    if (curr_level == 1) {
                        fraction.numerator = 1 + (int) (Math.random() * (multiplier * (curr_level + fields_num))); //zakres 1-100
                        fraction.denominator = 1 + (int) (Math.random() * (multiplier * (curr_level + fields_num)));

                }
                    else {
                        fraction.numerator = 1 + (int) (Math.random() * (multiplier2 * curr_level));
                        fraction.denominator = 1 + (int) (Math.random() * (multiplier2 * curr_level));

                    }
                }
                while (fractions.contains(fraction) || (fraction.numerator == fraction.denominator));

                fractions.add(fraction);

                if (instruction.equals("ułamki niewłaściwe")) {
                    if (fraction.numerator > fraction.denominator) {
                        counter ++;
                    }
                }

                else
                {
                    if (fraction.numerator < fraction.denominator) {
                        counter ++;
                    }
                }

                sGenerated[i] = String.valueOf(fraction.numerator) + "/" + String.valueOf(fraction.denominator);
            }
        }
        while (counter < (fields_num) / 2);

        return sGenerated;
    }

    @Override
    public int getCounter() {
        return counter;
    }

    @Override
    public boolean check(int sel_field) {
        if (instruction.equals("ułamki niewłaściwe")) {
            if (fractions.get(sel_field).numerator > fractions.get(sel_field).denominator) {
                return true;
            }
        }
        else {
            if (fractions.get(sel_field).numerator < fractions.get(sel_field).denominator) {
                return true;
            }
        }

        return false;
    }

    int[] generateForExample() {
        int[] fraction = new int[2];

        do {
            for (int i = 0; i < 2; i ++) {
                fraction[i] = 2 + (int) (Math.random() * ((99 - 2) + 1)); // generowanie liczb z zakresu 2 - 99
            }
        }
        while (fraction[0] > fraction[1]);


        return fraction;
    }
}
