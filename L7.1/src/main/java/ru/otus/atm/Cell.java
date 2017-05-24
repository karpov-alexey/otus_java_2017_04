package ru.otus.atm;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Alexey on 23.05.2017.
 */
public class Cell implements Comparable<Cell>, Iterable<Cell>, Cloneable {
    private final int nominal;
    private int count;
    private Cell next;

    public Cell(int nominal, int count) {
        this.nominal = nominal;
        this.count = count;
    }

    public boolean getBanknotes(Map<Integer, Integer> banknotesMap)
    {
        Integer expectedCount = banknotesMap.get(nominal);
        if (expectedCount == null)
            expectedCount = 0;

        boolean nextCellResult = true;

        if ( next != null) {
            nextCellResult = next.getBanknotes(banknotesMap);
        }

        if(nextCellResult) {
            count = count - expectedCount;
            return true;
        }
        return false;
    }

    void add(int count) {
        this.count += count;
    }

    public int getNominal() {
        return nominal;
    }

    public int getCount() {
        return count;
    }

    public void setNext(Cell next) {
        this.next = next;
    }

    public int getBalance() {
        return count * nominal;
    }

    @Override
    public int compareTo(Cell o) {
        if (nominal > o.getNominal())
            return -1;
        if (nominal < o.getNominal())
            return 1;
        return 0;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new Iterator<Cell>() {
            Cell current = Cell.this;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Cell next() {
                Cell before = current;
                current = current.next;
                return before;
            }
        };
    }

    @Override
    public Cell clone() {
        Cell clone = null;
        try {
            clone = (Cell)super.clone();
        }
        catch( CloneNotSupportedException ex ) {
            throw new InternalError();
        }

        if (next != null) {
            clone.next = next.clone();
        }

        return clone;
    }
}