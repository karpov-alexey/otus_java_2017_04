package ru.otus.algorithms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.otus.atm.Cell;
import ru.otus.atm.Cells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */
public class MinBanknotesWithdrawTest {

    private Cells cells = null;

    @Before
    public void beforeTest() {
        List<Cell> cellList = new ArrayList<>();
        cellList.add(new Cell(1, 0));
        cellList.add(new Cell(2, 0));
        cellList.add(new Cell(5, 0));
        cellList.add(new Cell(10, 0));
        cellList.add(new Cell(50, 0));
        cellList.add(new Cell(100, 0));
        cellList.add(new Cell(500, 0));
        cellList.add(new Cell(1000, 0));

        cells = new Cells(cellList);
    }

    @Test
    public void withdraw() throws Exception {
        Map<Integer, Integer> expect = new HashMap<>();
        Map<Integer, Integer> result = null;

        cells.addMoney(1, 0);
        cells.addMoney(2, 10);
        cells.addMoney(5, 0);
        cells.addMoney(10, 10);
        cells.addMoney(50, 10);
        cells.addMoney(100, 10);
        cells.addMoney(500, 10);
        cells.addMoney(1000, 10);

        result = new MinBanknotesWithdraw().withdraw(10, cells);
        expect.clear();
        expect.put(10, 1);
        Assert.assertEquals(expect, result);

        result = new MinBanknotesWithdraw().withdraw(110, cells);
        expect.clear();
        expect.put(10, 1);
        expect.put(100, 1);
        Assert.assertEquals(expect, result);

        result = new MinBanknotesWithdraw().withdraw(500, cells);
        expect.clear();
        expect.put(500, 1);
        Assert.assertEquals(expect, result);

        result = new MinBanknotesWithdraw().withdraw(501, cells);
        Assert.assertNull(result);

        result = new MinBanknotesWithdraw().withdraw(0, cells);
        Assert.assertNull(result);
    }
}