package ru.otus.atm;

import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public interface Atm {
    public int getId();
    public void addMoney(int nominal, int count);
    public Map<Integer, Integer> withdraw(int request);
    public int getBalance();
}
