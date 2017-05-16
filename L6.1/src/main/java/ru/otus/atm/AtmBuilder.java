package ru.otus.atm;

import ru.otus.algorithms.GetBanknotesAlgorithm;
import ru.otus.algorithms.SummAlgorithm;
import ru.otus.exception.AtmException;

/**
 * Created by Alexey on 16.05.2017.
 */
public class AtmBuilder {
    GetBanknotesAlgorithm getBanknotesAlgorithm = null;
    SummAlgorithm summAlgorithm = null;

    public void setGetBanknotesAlgorithm(GetBanknotesAlgorithm getBanknotesAlgorithm) {
        this.getBanknotesAlgorithm = getBanknotesAlgorithm;
    }

    public void setSummAlgorithm (SummAlgorithm summAlgorithm) {
        this.summAlgorithm = summAlgorithm;
    }

    public Atm createAtm() throws AtmException {
        if (getBanknotesAlgorithm == null) {
            throw new AtmException("Could not construct an object of atm: not found getBanknotesAlgorithm");
        }

        if (summAlgorithm == null) {
            throw new AtmException("Could not construct an object of atm: not found summAlgorithm");
        }

        return new SimpleAtm(getBanknotesAlgorithm, summAlgorithm);
    }
}
