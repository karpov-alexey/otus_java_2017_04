package ru.otus.atm;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by Alexey on 23.05.2017.
 */
public class CellTest {
    @Test
    public void add() throws Exception {
       Cell cell = new Cell(20, 50);
       cell.add(20);
       Assert.assertEquals(70, cell.getCount());
    }

    @Test
    public void getNominal() throws Exception {
        Cell cell = new Cell(20, 50);
        Assert.assertEquals(20, cell.getNominal());
    }

    @Test
    public void getCount() throws Exception {
        Cell cell = new Cell(20, 50);
        cell.add(20);
        Assert.assertEquals(70, cell.getCount());
    }

    @Test
    public void getBalance() throws Exception {
        Cell cell = new Cell(20, 50);
        Assert.assertEquals(1000, cell.getBalance());
    }

    @Test
    public void compareTo() throws Exception {
        Cell cell1 = new Cell(10, 20);
        Cell cell2 = new Cell(20,10);

        Assert.assertEquals(1, cell1.compareTo(cell2));
    }

    @Test
    public void iterator() throws Exception {
        Cell cell1 = new Cell(10, 50);
        Cell cell2 = new Cell(20, 50);
        Cell cell3 = new Cell(30, 50);
        cell1.setNext(cell2);
        cell2.setNext(cell3);

        int[] expected = {10, 20, 30};

        Iterator<Cell> iterator = cell1.iterator();
        int cnt = 0;
        while (iterator.hasNext()){
            Cell cell = iterator.next();
            Assert.assertEquals(expected[cnt], cell.getNominal());
            ++cnt;
        }

        Assert.assertEquals(3, cnt);

    }

}