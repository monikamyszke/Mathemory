package com.example.monikam.mathemory;

class IntegerPair {
    int numerator;
    int denominator;

// sprawdzanie czy ułamki są takie same
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntegerPair) {
            IntegerPair i = (IntegerPair) obj;
            return ((numerator == i.numerator) && (denominator == i.denominator));
        }
        return false;
    }
}
