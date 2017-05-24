package ru.otus.atm;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.algorithms.MinBanknotesWithdraw;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 16.05.2017.
 */
public class AtmBuilderTest {
    @Test
    public void createAtm() throws Exception {
        AtmBuilder atmBuilder = new AtmBuilder();
        atmBuilder.setGetBanknotesAlgorithm(new MinBanknotesWithdraw());
        atmBuilder.setId(1);

        List<Cell> cellList = new ArrayList<>();
        cellList.add(new Cell(1, 0));
        cellList.add(new Cell(2, 0));
        cellList.add(new Cell(5, 0));
        cellList.add(new Cell(10, 0));
        cellList.add(new Cell(50, 0));
        cellList.add(new Cell(100, 0));
        cellList.add(new Cell(500, 0));
        cellList.add(new Cell(1000, 0));
        atmBuilder.setCells(cellList);

        Atm atm = atmBuilder.createAtm();

        Assert.assertEquals(ru.otus.atm.SimpleAtm.class, atm.getClass());
    }

    @Test(expected = ru.otus.exception.AtmException.class)
    public void createAtm_noAlgorithm() throws Exception {
        AtmBuilder atmBuilder = new AtmBuilder();
        Atm atm = atmBuilder.createAtm();
    }
}