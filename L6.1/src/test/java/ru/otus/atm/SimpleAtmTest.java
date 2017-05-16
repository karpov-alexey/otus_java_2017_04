package ru.otus.atm;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.Money.Banknote;
import ru.otus.algorithms.GetMinimalBanknotes;
import ru.otus.algorithms.SimpleSummAlgorithm;
import ru.otus.exception.AtmException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public class SimpleAtmTest {


    @Test
    public void getBanknotes() throws Exception {

        Map<Banknote, Integer> expect = new HashMap<>();
        Map<Banknote, Integer> result = null;

        AtmBuilder atmBuilder = new AtmBuilder();
        atmBuilder.setGetBanknotesAlgorithm(new GetMinimalBanknotes());
        atmBuilder.setSummAlgorithm(new SimpleSummAlgorithm());
        Atm atm = atmBuilder.createAtm();

        atm.putBanknotes(Banknote.Banknote_1, 10);
        atm.putBanknotes(Banknote.Banknote_2, 10);
        atm.putBanknotes(Banknote.Banknote_5, 10);
        atm.putBanknotes(Banknote.Banknote_10, 10);
        atm.putBanknotes(Banknote.Banknote_50, 10);
        atm.putBanknotes(Banknote.Banknote_100, 10);
        atm.putBanknotes(Banknote.Banknote_500, 10);
        atm.putBanknotes(Banknote.Banknote_1000, 10);

        result = atm.getBanknotes(10);
        expect.clear();
        expect.put(Banknote.Banknote_10, 1);
        Assert.assertEquals(expect, result);

    }

    @Test
    public void getBalance() throws AtmException {
        AtmBuilder atmBuilder = new AtmBuilder();
        atmBuilder.setGetBanknotesAlgorithm(new GetMinimalBanknotes());
        atmBuilder.setSummAlgorithm(new SimpleSummAlgorithm());
        Atm atm = atmBuilder.createAtm();

        atm.putBanknotes(Banknote.Banknote_1, 1);
        atm.putBanknotes(Banknote.Banknote_2, 1);
        atm.putBanknotes(Banknote.Banknote_5, 1);
        atm.putBanknotes(Banknote.Banknote_10, 1);
        atm.putBanknotes(Banknote.Banknote_50, 1);
        atm.putBanknotes(Banknote.Banknote_100, 1);
        atm.putBanknotes(Banknote.Banknote_500, 1);
        atm.putBanknotes(Banknote.Banknote_1000, 1);

        Assert.assertEquals(1668, atm.getBalance());

        atm.putBanknotes(Banknote.Banknote_1, 1);
        atm.putBanknotes(Banknote.Banknote_2, 1);
        atm.putBanknotes(Banknote.Banknote_5, 1);
        atm.putBanknotes(Banknote.Banknote_10, 1);
        atm.putBanknotes(Banknote.Banknote_50, 1);
        atm.putBanknotes(Banknote.Banknote_100, 1);
        atm.putBanknotes(Banknote.Banknote_500, 1);
        atm.putBanknotes(Banknote.Banknote_1000, 1);

        Assert.assertEquals(1668 * 2, atm.getBalance());
    }
}
