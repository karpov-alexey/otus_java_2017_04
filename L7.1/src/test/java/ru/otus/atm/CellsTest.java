package ru.otus.atm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexey on 23.05.2017.
 */
public class CellsTest {
    Cells cells = null;

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
    public void addMoney() throws Exception {
        cells.addMoney(10, 50);
        cells.addMoney(20, 50);
        cells.addMoney(50, 10);

        int balance = 0;
        for (Cell cell : cells) {
            balance += cell.getBalance();
        }
        Assert.assertEquals(1000, balance);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(8, cells.size());

    }

    @Test
    public void iterator() throws Exception {

        Iterator<Cell> iterator = cells.iterator();
        int[] expected = {1000, 500, 100, 50, 10, 5, 2, 1};
        int cnt = 0;
        while (iterator.hasNext()) {
            Assert.assertEquals(expected[cnt], iterator.next().getNominal());
            ++cnt;
        }

        Assert.assertEquals(8, cnt);

    }

}