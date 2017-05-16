package ru.otus.atm;

import ru.otus.Money.Banknote;

import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public interface Atm {
    public void putBanknotes(Banknote banknote, int count);
    public Map<Banknote, Integer> getBanknotes(int summ);
    public int getBalance();
}
