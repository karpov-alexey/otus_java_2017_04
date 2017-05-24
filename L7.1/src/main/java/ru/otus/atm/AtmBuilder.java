package ru.otus.atm;

import ru.otus.algorithms.WithdrawAlgorithm;
import ru.otus.exception.AtmException;

import java.util.List;

/**
 * Created by Alexey on 16.05.2017.
 */
public class AtmBuilder {
    WithdrawAlgorithm withdrawAlgorithm = null;
    int id = -1;
    List<Cell> cells = null;

    public void setGetBanknotesAlgorithm(WithdrawAlgorithm withdrawAlgorithm) {
        this.withdrawAlgorithm = withdrawAlgorithm;
    }

    public void setId (int id) {
        this.id = id;
    }

    public void setCells (List<Cell> cells) {
        this.cells = cells;
    }

    public Atm createAtm() throws AtmException {
        if (withdrawAlgorithm == null) {
            throw new AtmException("Could not construct an object of atm: not found getBanknotesAlgorithm");
        }

        if (id < 0) {
            throw new AtmException("Could not construct an object of atm: not found id");
        }

        if (cells == null) {
            throw new AtmException("Could not construct an object of atm: not found ceil list");
        }

        return new SimpleAtm(id, withdrawAlgorithm, cells);
    }
}
