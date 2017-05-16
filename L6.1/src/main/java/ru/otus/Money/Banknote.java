package ru.otus.Money;

/**
 * Created by Alexey on 16.05.2017.
 */
public enum Banknote {
    Banknote_1(1),
    Banknote_2(2),
    Banknote_5(5),
    Banknote_10(10),
    Banknote_50(50),
    Banknote_100(100),
    Banknote_500(500),
    Banknote_1000(1000);

    int value = 0;

    Banknote(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static public Banknote getBanknote(int value) {
        for (Banknote banknote: Banknote.values()) {
            if (banknote.getValue() == value) {
                return banknote;
            }
        }
        return null;
    }
}
