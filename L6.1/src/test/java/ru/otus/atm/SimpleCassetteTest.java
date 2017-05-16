package ru.otus.atm;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.Money.Banknote;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public class SimpleCassetteTest {
    @Test
    public void getBanknotes() throws Exception {
        Cassete cassete = new SimpleCassette();

        cassete.putBanknotes(Banknote.Banknote_1, 1);
        cassete.putBanknotes(Banknote.Banknote_2, 1);
        cassete.putBanknotes(Banknote.Banknote_5, 1);
        cassete.putBanknotes(Banknote.Banknote_10, 1);
        cassete.putBanknotes(Banknote.Banknote_50, 1);
        cassete.putBanknotes(Banknote.Banknote_100, 1);
        cassete.putBanknotes(Banknote.Banknote_500, 1);
        cassete.putBanknotes(Banknote.Banknote_1000, 1);

        Map<Banknote, Integer> map = new HashMap<>();
        map.put(Banknote.Banknote_1, 1);
        map.put(Banknote.Banknote_2, 1);
        map.put(Banknote.Banknote_5, 1);
        map.put(Banknote.Banknote_10, 1);
        map.put(Banknote.Banknote_50, 1);
        map.put(Banknote.Banknote_100, 1);
        map.put(Banknote.Banknote_500, 1);
        map.put(Banknote.Banknote_1000, 1);

        Assert.assertEquals(map, cassete.getBanknotes());

        cassete.putBanknotes(Banknote.Banknote_1, 1);
        cassete.putBanknotes(Banknote.Banknote_2, 1);
        cassete.putBanknotes(Banknote.Banknote_5, 1);
        cassete.putBanknotes(Banknote.Banknote_10, 1);
        cassete.putBanknotes(Banknote.Banknote_50, 1);
        cassete.putBanknotes(Banknote.Banknote_100, 1);
        cassete.putBanknotes(Banknote.Banknote_500, 1);
        cassete.putBanknotes(Banknote.Banknote_1000, 1);

        Map<Banknote, Integer> map1 = new HashMap<>();
        map1.put(Banknote.Banknote_1, 2);
        map1.put(Banknote.Banknote_2, 2);
        map1.put(Banknote.Banknote_5, 2);
        map1.put(Banknote.Banknote_10, 2);
        map1.put(Banknote.Banknote_50, 2);
        map1.put(Banknote.Banknote_100, 2);
        map1.put(Banknote.Banknote_500, 2);
        map1.put(Banknote.Banknote_1000, 2);

        Assert.assertEquals(map1, cassete.getBanknotes());
    }

}