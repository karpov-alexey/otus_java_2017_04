package ru.otus.atm;

import ru.otus.Money.Banknote;
import ru.otus.algorithms.GetBanknotesAlgorithm;
import ru.otus.algorithms.SummAlgorithm;

import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
class SimpleAtm implements Atm {

    Cassete cassete = new SimpleCassette();

    private GetBanknotesAlgorithm getBanknotesAlgorithm;
    private SummAlgorithm summAlgorithm;

    public SimpleAtm() {};
    public SimpleAtm(GetBanknotesAlgorithm getBanknotesAlgorithm, SummAlgorithm summAlgorithm) {
        this.getBanknotesAlgorithm = getBanknotesAlgorithm;
        this.summAlgorithm = summAlgorithm;
    };


    @Override
    public void putBanknotes(Banknote banknote, int count) {
        cassete.putBanknotes(banknote, count);
    }

    @Override
    public Map<Banknote, Integer> getBanknotes(int summ) {
        return getBanknotesAlgorithm.getBanknotes(summ, cassete);
    }

    @Override
    public int getBalance() {
        return summAlgorithm.getSumm(cassete);
    }
}
