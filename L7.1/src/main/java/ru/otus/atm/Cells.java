package ru.otus.atm;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexey on 23.05.2017.
 */
public class Cells implements Iterable<Cell>, Cloneable{

    private Cell first;
    int size = 0;

    public Cells(List<Cell> cells) {
        Collections.sort(cells);
        first = cells.get(0);
        linkCells(cells);
    }

    private void linkCells(List<Cell> cells) {
        Iterator<Cell> iterator = cells.iterator();
        Cell cellA = iterator.next();
        ++size;
        while (iterator.hasNext()) {
            ++size;
            Cell cellB = iterator.next();
            cellA.setNext(cellB);
            cellA = cellB;
        }
    }

    public void addMoney(int nominal, int count) {
        Iterator<Cell> iterator = iterator();
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            if (nominal == cell.getNominal()) {
                cell.add(count);
                return;
            }
        }
        //error
    }

    boolean getBanknotes(Map<Integer, Integer> banknotesMap) {
        return first.getBanknotes(banknotesMap);
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Cell> iterator() {
        return first.iterator();
    }

    @Override
    public Cells clone() {
        Cells clone = null;
        try {
            clone = (Cells)super.clone();
        }
        catch( CloneNotSupportedException ex ) {
            throw new InternalError();
        }

        clone.first = first.clone();
        return clone;
    }

    //public Map<Banknote, Integer> withdraw();
}
