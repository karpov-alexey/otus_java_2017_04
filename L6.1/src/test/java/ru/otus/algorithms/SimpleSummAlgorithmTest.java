package ru.otus.algorithms;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.Money.Banknote;
import ru.otus.atm.Cassete;
import ru.otus.atm.SimpleCassette;

/**
 * Created by Alexey on 16.05.2017.
 */
public class SimpleSummAlgorithmTest {
    @Test
    public void getSumm() throws Exception {

        Cassete cassete = new SimpleCassette();

        cassete.putBanknotes(Banknote.Banknote_1, 1);
        cassete.putBanknotes(Banknote.Banknote_2, 1);
        cassete.putBanknotes(Banknote.Banknote_5, 1);
        cassete.putBanknotes(Banknote.Banknote_10, 1);
        cassete.putBanknotes(Banknote.Banknote_50, 1);
        cassete.putBanknotes(Banknote.Banknote_100, 1);
        cassete.putBanknotes(Banknote.Banknote_500, 1);
        cassete.putBanknotes(Banknote.Banknote_1000, 1);

        Assert.assertEquals(1668, new SimpleSummAlgorithm().getSumm(cassete));

        cassete.putBanknotes(Banknote.Banknote_1, 1);
        cassete.putBanknotes(Banknote.Banknote_2, 1);
        cassete.putBanknotes(Banknote.Banknote_5, 1);
        cassete.putBanknotes(Banknote.Banknote_10, 1);
        cassete.putBanknotes(Banknote.Banknote_50, 1);
        cassete.putBanknotes(Banknote.Banknote_100, 1);
        cassete.putBanknotes(Banknote.Banknote_500, 1);
        cassete.putBanknotes(Banknote.Banknote_1000, 1);

        Assert.assertEquals(1668 * 2, new SimpleSummAlgorithm().getSumm(cassete));

    }

}