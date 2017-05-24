package ru.otus.department;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.algorithms.MinBanknotesWithdraw;
import ru.otus.atm.Atm;
import ru.otus.atm.AtmBuilder;
import ru.otus.atm.Cell;
import ru.otus.exception.AtmException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexey on 24.05.2017.
 */
public class AtmStorageTest {

    @Test
    public void addAtm() throws Exception {
        AtmStorage atmStorage = new AtmStorage();
        atmStorage.addAtm(createAtm(1));
        Assert.assertEquals(1, atmStorage.getAtmCount());
        atmStorage.addAtm(createAtm(2));
        Assert.assertEquals(2, atmStorage.getAtmCount());
    }

    @Test
    public void iterator() throws Exception {
        AtmStorage atmStorage = new AtmStorage();
        atmStorage.addAtm(createAtm(1));
        atmStorage.addAtm(createAtm(2));
        Iterator<Atm> atmIterator = atmStorage.iterator();

        int cnt = 0;
        int[] expected = {1, 2};
        while (atmIterator.hasNext()) {
            Assert.assertEquals(expected[cnt], atmIterator.next().getId());
            ++cnt;
        }
        Assert.assertEquals(2, cnt);
    }

    private Atm createAtm(int id) throws AtmException {
        AtmBuilder atmBuilder = new AtmBuilder();
        atmBuilder.setGetBanknotesAlgorithm(new MinBanknotesWithdraw());
        atmBuilder.setId(id);

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

        return atmBuilder.createAtm();
    }

}