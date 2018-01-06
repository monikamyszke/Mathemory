package com.example.monikam.mathemory;

/**
 * Klasa reprezentująca ułamek
 */
class IntegerPair {
    /**Licznik ułamka*/
    int numerator;
    /**Mianownik ułamka*/
    int denominator;

    /**
     * Nadpisanie metody sprawdzającej, czy obiekty (tu ułamki) są takie same
     * @param obj obiekt klasy Object
     * @return true, jeżeli obiekty są takie same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntegerPair) {
            IntegerPair i = (IntegerPair) obj;
            return ((numerator == i.numerator) && (denominator == i.denominator));
        }
        return false;
    }
}
