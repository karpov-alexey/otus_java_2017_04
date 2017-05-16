package ru.otus.atm;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.algorithms.GetMinimalBanknotes;
import ru.otus.algorithms.SimpleSummAlgorithm;

/**
 * Created by Alexey on 16.05.2017.
 */
public class AtmBuilderTest {
    @Test
    public void createAtm() throws Exception {
        AtmBuilder atmBuilder = new AtmBuilder();
        atmBuilder.setGetBanknotesAlgorithm(new GetMinimalBanknotes());
        atmBuilder.setSummAlgorithm(new SimpleSummAlgorithm());
        Atm atm = atmBuilder.createAtm();

        Assert.assertEquals(ru.otus.atm.SimpleAtm.class, atm.getClass());
    }

    @Test(expected = ru.otus.exception.AtmException.class)
    public void createAtm_noAlgorithm() throws Exception {
        AtmBuilder atmBuilder = new AtmBuilder();
        Atm atm = atmBuilder.createAtm();
    }
}