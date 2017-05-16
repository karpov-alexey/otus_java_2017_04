package ru.otus.atm;

import ru.otus.Money.Banknote;
import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public interface Cassete {
    public void putBanknotes(Banknote banknote, int count);
    public Map<Banknote, Integer> getBanknotes();
}
