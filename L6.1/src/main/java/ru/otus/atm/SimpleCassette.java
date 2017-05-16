package ru.otus.atm;

import ru.otus.Money.Banknote;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public class SimpleCassette implements Cassete {
    private Map<Banknote, Integer> banknotes = new HashMap<>();

    @Override
    public void putBanknotes(Banknote banknote, int count) {
        if (!banknotes.containsKey(banknote))
        {
            banknotes.put(banknote, count);
        } else {
            Integer currentCount = banknotes.get(banknote);
            banknotes.put(banknote, currentCount + count);
        }
    }

    public Map<Banknote, Integer> getBanknotes() {
        return banknotes;
    }
}
