package ru.otus;

import ru.otus.Money.Banknote;
import ru.otus.algorithms.GetMinimalBanknotes;
import ru.otus.algorithms.SimpleSummAlgorithm;
import ru.otus.atm.Atm;
import ru.otus.atm.AtmBuilder;
import ru.otus.exception.AtmException;

/**
 * Created by Alexey on 16.05.2017.
 */
public class SimpleAtmExample {
    public void run() throws AtmException {
        Atm atm = getAtm();
        putBanknotes(atm);
        System.out.println(atm.getBalance());
    }

    private void putBanknotes(Atm atm) {
        atm.putBanknotes(Banknote.Banknote_1, 10);
        atm.putBanknotes(Banknote.Banknote_2, 10);
        atm.putBanknotes(Banknote.Banknote_5, 10);
        atm.putBanknotes(Banknote.Banknote_10, 10);
        atm.putBanknotes(Banknote.Banknote_50, 10);
        atm.putBanknotes(Banknote.Banknote_100, 10);
        atm.putBanknotes(Banknote.Banknote_500, 10);
        atm.putBanknotes(Banknote.Banknote_1000, 10);
    }

    private Atm getAtm() throws AtmException {
        AtmBuilder atmBuilder = new AtmBuilder();
        atmBuilder.setGetBanknotesAlgorithm(new GetMinimalBanknotes());
        atmBuilder.setSummAlgorithm(new SimpleSummAlgorithm());
        return atmBuilder.createAtm();
    }


}
