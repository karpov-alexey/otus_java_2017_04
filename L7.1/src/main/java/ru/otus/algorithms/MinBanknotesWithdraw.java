package ru.otus.algorithms;

import ru.otus.atm.Cell;
import ru.otus.atm.Cells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public class MinBanknotesWithdraw implements WithdrawAlgorithm {
    @Override
    public Map<Integer, Integer> withdraw(int request, Cells cells) {
        int size = cells.size();
        int[] values = new int[size];
        int[] ammounts = new int[size];

        int cnt = 0;
        for (Cell cell : cells) {
            values[cnt] = cell.getNominal();
            ammounts[cnt] = cell.getCount();
            ++cnt;
        }

        List<Integer[]> results = solutions(values, ammounts, new int[size], request, 0);

        int minimumBanknotes = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < results.size(); ++i){
            Integer banknoteCounter = 0;
            for (Integer count : results.get(i)) {
                banknoteCounter += count;
            }

            if (banknoteCounter == 0)
                continue;

            if (banknoteCounter < minimumBanknotes) {
                index = i;
                minimumBanknotes = banknoteCounter;
            }
        }

        Map<Integer, Integer> map = null;
        if (index >= 0) {
            Integer[] res = results.get(index);
            map = new HashMap<>();
            for (int i = 0; i < res.length; ++i) {
                if (res[i] > 0)
                    map.put(values[i], res[i]);
            }
        }
        return map;
    }

    private List<Integer[]> solutions(int[] values, int[] ammounts, int[] variation, int price, int position){
        List<Integer[]> list = new ArrayList<>();
        int value = compute(values, variation);
        if (value < price){
            for (int i = position; i < values.length; i++) {
                if (ammounts[i] > variation[i]){
                    int[] newvariation = variation.clone();
                    newvariation[i]++;
                    List<Integer[]> newList = solutions(values, ammounts, newvariation, price, i);
                    if (newList != null){
                        list.addAll(newList);
                    }
                }
            }
        } else if (value == price) {
            list.add(myCopy(variation));
        }
        return list;
    }

    private int compute(int[] values, int[] variation){
        int ret = 0;
        for (int i = 0; i < variation.length; i++) {
            ret += values[i] * variation[i];
        }
        return ret;
    }

    private Integer[] myCopy(int[] ar){
        Integer[] ret = new Integer[ar.length];
        for (int i = 0; i < ar.length; i++) {
            ret[i] = ar[i];
        }
        return ret;
    }
}
