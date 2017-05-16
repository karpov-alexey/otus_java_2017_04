package ru.otus.algorithms;

import ru.otus.Money.Banknote;
import ru.otus.atm.Cassete;

import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public interface GetBanknotesAlgorithm {
    public Map<Banknote, Integer> getBanknotes(int summ, Cassete cassette);
}
