package ru.otus.algorithms;

import ru.otus.atm.Cells;

import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public interface WithdrawAlgorithm {
    public Map<Integer, Integer> withdraw(int request, Cells cells);
}
