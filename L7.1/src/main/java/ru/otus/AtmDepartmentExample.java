package ru.otus;

import ru.otus.algorithms.MinBanknotesWithdraw;
import ru.otus.atm.Atm;
import ru.otus.atm.AtmBuilder;
import ru.otus.atm.Cell;
import ru.otus.department.AtmDepartment;
import ru.otus.exception.AtmException;
import ru.otus.observer.AtmNotifier;
import ru.otus.observer.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 16.05.2017.
 */
public class AtmDepartmentExample {

    AtmDepartment atmDepartment = null;

    public void run() throws AtmException {
        atmDepartment = new AtmDepartment();
        atmDepartment.addAtm(createAtm1());
        atmDepartment.addAtm(createAtm2());
        atmDepartment.printBalance();

        System.out.println("After withdraw");
        atmDepartment.withdraw(1, 10);
        atmDepartment.withdraw(2, 100);
        atmDepartment.printBalance();

        AtmNotifier.getNotifier().notify(Event.RESTORE_STATE);
        atmDepartment.printBalance();
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
