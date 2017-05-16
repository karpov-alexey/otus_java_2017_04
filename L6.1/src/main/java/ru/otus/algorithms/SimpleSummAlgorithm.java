package ru.otus.algorithms;

import ru.otus.Money.Banknote;
import ru.otus.atm.Cassete;

import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public class SimpleSummAlgorithm implements SummAlgorithm{
    @Override
    public int getSumm(Cassete cassete) {
        int summ = 0;
        for (Map.Entry<Banknote, Integer> pair : cassete.getBanknotes().entrySet())
        {
            Banknote banknote = pair.getKey();
            Integer count = pair.getValue();
            summ += banknote.getValue() * count;
        }
        return summ;
    }
}
