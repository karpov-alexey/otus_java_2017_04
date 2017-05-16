package ru.otus.algorithms;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.Money.Banknote;
import ru.otus.atm.Cassete;
import ru.otus.atm.SimpleCassette;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public class GetMinimalBanknotesTest {
    @Test
    public void getBanknotes() throws Exception {
        Map<Banknote, Integer> expect = new HashMap<>();
        Map<Banknote, Integer> result = null;

        Cassete cassete = new SimpleCassette();
        cassete.putBanknotes(Banknote.Banknote_1, 10);
        cassete.putBanknotes(Banknote.Banknote_2, 10);
        cassete.putBanknotes(Banknote.Banknote_5, 10);
        cassete.putBanknotes(Banknote.Banknote_10, 10);
        cassete.putBanknotes(Banknote.Banknote_50, 10);
        cassete.putBanknotes(Banknote.Banknote_100, 10);
        cassete.putBanknotes(Banknote.Banknote_500, 10);
        cassete.putBanknotes(Banknote.Banknote_1000, 10);

        result = new GetMinimalBanknotes().getBanknotes(10, cassete);
        expect.clear();
        expect.put(Banknote.Banknote_10, 1);
        Assert.assertEquals(expect, result);

        result = new GetMinimalBanknotes().getBanknotes(110, cassete);
        expect.clear();
        expect.put(Banknote.Banknote_10, 1);
        expect.put(Banknote.Banknote_100, 1);
        Assert.assertEquals(expect, result);

        result = new GetMinimalBanknotes().getBanknotes(10000000, cassete);
        expect.clear();
        expect.put(Banknote.Banknote_10, 1);
        expect.put(Banknote.Banknote_100, 1);
        Assert.assertEquals(expect, result);


    }

}