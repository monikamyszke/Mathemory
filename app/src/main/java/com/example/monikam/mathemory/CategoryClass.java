package com.example.monikam.mathemory;

abstract class CategoryClass {

    public abstract String getInstruction(int curr_level); // funkcja ustawiająca treść zadania

    public abstract String[] generateNumbers(int fields_num, int curr_level); // funkcja generująca liczby dla plansz

    public abstract int getCounter(); // funkcja zwracając ilość wygenerowanych liczb spełniających warunek

    public abstract boolean check(int sel_field); // funkcja sprawdzająca poprawność

}
