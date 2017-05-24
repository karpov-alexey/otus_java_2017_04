package ru.otus.atm;

import ru.otus.algorithms.WithdrawAlgorithm;
import ru.otus.observer.AtmNotifier;
import ru.otus.observer.Event;
import ru.otus.observer.Listener;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexey on 16.05.2017.
 */



class SimpleAtm implements Atm {

    private class SaveState {
        private Cells cells = null;
        SaveState(Cells cells) {
            this.cells = cells;
        }

        private Cells getCells() {
            return cells;
        }
    }

    private int id = -1;
    private SaveState saveState = null;
    private Cells cells =  null;

    private WithdrawAlgorithm withdrawAlgorithm;

    private Listener saveListener = new Listener() {
        @Override
        public void handleEvent(Event event) {
            System.out.println("id = " + id + ": SAVE_EVENT");
            saveState();
        }
    };

    private Listener restoreListener = new Listener() {
        @Override
        public void handleEvent(Event event) {
            System.out.println("id = " + id + ": RESTORE_EVENT");
            restoreState();
        }
    };

    public SimpleAtm() {};
    public SimpleAtm(int id, WithdrawAlgorithm withdrawAlgorithm, List<Cell> cells ) {

        this.id = id;
        this.cells = new Cells(cells);
        this.withdrawAlgorithm = withdrawAlgorithm;
        AtmNotifier.getNotifier().submit(Event.SAVE_STATE, saveListener);
        AtmNotifier.getNotifier().submit(Event.RESTORE_STATE, restoreListener);
        saveState();
    };

    private void saveState() {
        saveState = new SaveState(cells.clone());

    }

    private void restoreState() {
        cells = saveState.getCells().clone();
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void addMoney(int nominal, int count) {
        cells.addMoney(nominal, count);
    }

    @Override
    public Map<Integer, Integer> withdraw(int request) {
        Map<Integer, Integer> banknotesMap = withdrawAlgorithm.withdraw(request, cells);
        if (cells.getBanknotes(banknotesMap)) {
            return banknotesMap;
        }

        return null;
    }

    @Override
    public int getBalance() {
        int balance = 0;
        for (Cell cell : cells) {
            balance += cell.getBalance();
        }
        return balance;//summAlgorithm.getSumm(cells);
    }
}
