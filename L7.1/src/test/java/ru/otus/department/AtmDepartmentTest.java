package ru.otus.department;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.algorithms.MinBanknotesWithdraw;
import ru.otus.atm.Atm;
import ru.otus.atm.AtmBuilder;
import ru.otus.atm.Cell;
import ru.otus.exception.AtmException;
import ru.otus.observer.AtmNotifier;
import ru.otus.observer.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexey on 24.05.2017.
 */
public class AtmDepartmentTest {
    @Test
    public void addAtm() throws Exception {
        AtmDepartment atmDepartment = new AtmDepartment();
        atmDepartment.addAtm(createAtm1());
        atmDepartment.addAtm(createAtm2());
        Assert.assertEquals(2, atmDepartment.getAtmCount());

    }

    @Test
    public void getBalance() throws Exception {
        AtmDepartment atmDepartment = new AtmDepartment();
        atmDepartment.addAtm(createAtm1());
        atmDepartment.addAtm(createAtm2());
        Map<Integer, Integer> idBalanceMap = atmDepartment.getBalance();
        Assert.assertEquals(16680, (int)idBalanceMap.get(1));
        Assert.assertEquals(16680 * 2, (int)idBalanceMap.get(2));
    }

    @Test
    public void withdraw() throws Exception {
        AtmDepartment atmDepartment = new AtmDepartment();
        atmDepartment.addAtm(createAtm1());
        atmDepartment.addAtm(createAtm2());
        atmDepartment.withdraw(1, 10);
        atmDepartment.withdraw(2, 100);
        Map<Integer, Integer> idBalanceMap = atmDepartment.getBalance();
        Assert.assertEquals(16680 - 10, (int)idBalanceMap.get(1));
        Assert.assertEquals(16680 * 2 - 100, (int)idBalanceMap.get(2));
    }

    @Test
    public void restoreEvent() throws Exception {
        AtmDepartment atmDepartment = new AtmDepartment();
        atmDepartment.addAtm(createAtm1());
        atmDepartment.addAtm(createAtm2());
        atmDepartment.withdraw(1, 10);
        atmDepartment.withdraw(2, 100);
        Map<Integer, Integer> idBalanceMap = atmDepartment.getBalance();
        Assert.assertEquals(16680 - 10, (int)idBalanceMap.get(1));
        Assert.assertEquals(16680 * 2 - 100, (int)idBalanceMap.get(2));
        AtmNotifier.getNotifier().notify(Event.RESTORE_STATE);
        idBalanceMap = atmDepartment.getBalance();
        Assert.assertEquals(16680, (int)idBalanceMap.get(1));
        Assert.assertEquals(16680 * 2, (int)idBalanceMap.get(2));
    }


    private Atm createAtm1() throws AtmException {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(1, 10));
        cells.add(new Cell(2, 10));
        cells.add(new Cell(5, 10));
        cells.add(new Cell(10, 10));
        cells.add(new Cell(50, 10));
        cells.add(new Cell(100, 10));
        cells.add(new Cell(500, 10));
        cells.add(new Cell(1000, 10));
        return createAtm(1, cells);
    }

    private Atm createAtm2() throws AtmException {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(1, 20));
        cells.add(new Cell(2, 20));
        cells.add(new Cell(5, 20));
        cells.add(new Cell(10, 20));
        cells.add(new Cell(50, 20));
        cells.add(new Cell(100, 20));
        cells.add(new Cell(500, 20));
        cells.add(new Cell(1000, 20));
        return createAtm(2, cells);
    }

    private Atm createAtm(int id, List<Cell> cells) throws AtmException {
        AtmBuilder atmBuilder = new AtmBuilder();
        atmBuilder.setGetBanknotesAlgorithm(new MinBanknotesWithdraw());
        atmBuilder.setId(id);
        atmBuilder.setCells(cells);
        return atmBuilder.createAtm();
    }

}