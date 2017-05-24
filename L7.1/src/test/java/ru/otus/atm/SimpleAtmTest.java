package ru.otus.atm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.otus.algorithms.MinBanknotesWithdraw;
import ru.otus.exception.AtmException;
import ru.otus.observer.AtmNotifier;
import ru.otus.observer.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public class SimpleAtmTest {

    Atm atm  = null;

    @Before
    public void beforeTest() throws AtmException {
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

        atm = atmBuilder.createAtm();
    }

    @Test
    public void getMoney() throws Exception {

        Map<Integer, Integer> expect = new HashMap<>();
        Map<Integer, Integer> result = null;

        atm.addMoney(1, 10);
        atm.addMoney(2, 10);
        atm.addMoney(5, 10);
        atm.addMoney(10, 10);
        atm.addMoney(50, 10);
        atm.addMoney(100, 10);
        atm.addMoney(500, 10);
        atm.addMoney(1000, 10);

        result = atm.withdraw(10);
        expect.clear();
        expect.put(10, 1);
        Assert.assertEquals(expect, result);
        Assert.assertEquals(16670, atm.getBalance());

    }

    @Test
    public void getBalance() throws AtmException {

        atm.addMoney(1, 1);
        atm.addMoney(2, 1);
        atm.addMoney(5, 1);
        atm.addMoney(10, 1);
        atm.addMoney(50, 1);
        atm.addMoney(100, 1);
        atm.addMoney(500, 1);
        atm.addMoney(1000, 1);

        Assert.assertEquals(1668, atm.getBalance());

        atm.addMoney(1, 1);
        atm.addMoney(2, 1);
        atm.addMoney(5, 1);
        atm.addMoney(10, 1);
        atm.addMoney(50, 1);
        atm.addMoney(100, 1);
        atm.addMoney(500, 1);
        atm.addMoney(1000, 1);

        Assert.assertEquals(1668 * 2, atm.getBalance());
    }

    @Test
    public void restoreEvent() throws Exception {
        AtmBuilder atmBuilder = new AtmBuilder();
        atmBuilder.setGetBanknotesAlgorithm(new MinBanknotesWithdraw());
        atmBuilder.setId(1);

        List<Cell> cellList = new ArrayList<>();
        cellList.add(new Cell(1, 10));
        cellList.add(new Cell(2, 10));
        cellList.add(new Cell(5, 10));
        cellList.add(new Cell(10, 10));
        cellList.add(new Cell(50, 10));
        cellList.add(new Cell(100, 10));
        cellList.add(new Cell(500, 10));
        cellList.add(new Cell(1000, 10));
        atmBuilder.setCells(cellList);

        atm = atmBuilder.createAtm();

        Assert.assertEquals(16680, atm.getBalance());
        atm.withdraw(100);
        Assert.assertEquals(16680 - 100, atm.getBalance());

        AtmNotifier.getNotifier().notify(Event.RESTORE_STATE);
        Assert.assertEquals(16680, atm.getBalance());
    }
}
