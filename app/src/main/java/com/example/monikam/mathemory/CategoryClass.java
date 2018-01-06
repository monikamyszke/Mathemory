package com.example.monikam.mathemory;

/**
 * Klasa abstrakcyjna z zadeklarowanymi metodami dotyczącymi rozgrywki w kategorii
 */
public abstract class CategoryClass {

    /**
     * Funkcja ustawiająca treść zadania
     * @param curr_level aktualny poziom
     * @return treść polecenia wyświetlana na górze ekranu
    */
    public abstract String getInstruction(int curr_level);

    /**
     * Funkcja generująca liczby dla plansz
     * @param fields_num liczba pól w danym poziomie
     * @param curr_level aktualny poziom
     * @return tablica wygenerowanych wartości
     */
    public abstract String[] generateNumbers(int fields_num, int curr_level);

    /**
     * Funkcja zwracająca ilość wygenerowanych liczb spełniających warunek zadania
     * @return ilość liczb
     */
    public abstract int getCounter();

    /**
     * Funkcja sprawdzająca poprawność zaznaczonego pola
     * @param sel_field wybrane pole z planszy (jego indeks w tablicy)
     * @return true, jeżeli wybrano poprawne pole
     */
    public abstract boolean check(int sel_field);

}
