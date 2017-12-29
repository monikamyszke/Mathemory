package com.example.monikam.mathemory;

import java.util.HashSet;
import java.util.Set;

class Divisibility extends CategoryClass {

    private int[] generated;
    private String instruction;
    private int counter;

    @Override
    public String getInstruction(int curr_level) {

        if (curr_level == 3 || curr_level == 6 || curr_level == 9) {
            instruction = "Wybierz liczby podzielne przez 5:";
        }
        else {
            instruction = "Wybierz liczby podzielne przez 3:";
        }

        return instruction;
    }

    @Override
    public String[] generateNumbers(int fields_num, int curr_level) {

        generated = new int[fields_num];
        String[] sGenerated = new String[fields_num];
        Set<Integer> numbers = new HashSet<>();

        do {
            numbers.clear();
            counter = 0;

            for (int i = 0; i < fields_num; i++) {

                do {
                    int multiplier = 10;
                    if (curr_level == 1) {
                        generated[i] = 1 + (int) (Math.random() * (multiplier * (curr_level + fields_num)));
                    }
                    else {
                        generated[i] = 1 + (int) (Math.random() * (multiplier * curr_level - multiplier));
                    }
                }
                while (numbers.contains(generated[i]));

                numbers.add(generated[i]);

                if (instruction.equals("Wybierz liczby podzielne przez 3:")) {
                    if ((generated[i]) % 3 == 0) {
                        counter ++;
                    }
                }

                else
                    {
                    if ((generated[i]) % 5 == 0) {
                        counter ++;
                    }
                }

                sGenerated[i] = String.valueOf(generated[i]);
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

        if (instruction.equals("Wybierz liczby podzielne przez 3:")) {
            if (generated[sel_field] % 3 == 0) {
                return true;
            }
        }
        else {
            if (generated[sel_field] % 5 == 0) {
                return true;
            }
        }

        return false;
    }
}
