package ru.otus.algorithms;

import ru.otus.Money.Banknote;
import ru.otus.atm.Cassete;

import java.util.*;

/**
 * Created by Alexey on 16.05.2017.
 */
public class GetMinimalBanknotes implements GetBanknotesAlgorithm {
    @Override
    public Map<Banknote, Integer> getBanknotes(int summ, Cassete cassette) {
        int[] values = new int[cassette.getBanknotes().size()];
        int[] ammounts = new int[cassette.getBanknotes().size()];

        int cnt = 0;
        for (Map.Entry<Banknote, Integer> pair : cassette.getBanknotes().entrySet())
        {
            values[cnt] = pair.getKey().getValue();
            ammounts[cnt] = pair.getValue();
            ++cnt;
        }

        List<Integer[]> results = solutions(values, ammounts, new int[cassette.getBanknotes().size()], summ, 0);

        int minimumBanknotes = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < results.size(); ++i){
            Integer banknoteCounter = 0;
            for (Integer count : results.get(i))
            {
                banknoteCounter += count;
            }

            if (banknoteCounter == 0)
                continue;

            if (banknoteCounter < minimumBanknotes) {
                index = i;
                minimumBanknotes = banknoteCounter;
            }
        }

        Map<Banknote, Integer> map = null;

        if (index >= 0)
        {
            Integer[] res = results.get(index);
            map = new HashMap<>();
            for (int i = 0; i < res.length; ++i)
            {
                if (res[i] > 0)
                    map.put(Banknote.getBanknote(values[i]), res[i]);
            }
        }

        System.out.println(map);

        return map;
    }

    public List<Integer[]> solutions(int[] values, int[] ammounts, int[] variation, int price, int position){
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

    public int compute(int[] values, int[] variation){
        int ret = 0;
        for (int i = 0; i < variation.length; i++) {
            ret += values[i] * variation[i];
        }
        return ret;
    }

    public Integer[] myCopy(int[] ar){
        Integer[] ret = new Integer[ar.length];
        for (int i = 0; i < ar.length; i++) {
            ret[i] = ar[i];
        }
        return ret;
    }

}
