package ru.otus.department;

import ru.otus.atm.Atm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexey on 23.05.2017.
 */
class AtmStorage {
    private List<Atm> atms = new LinkedList<>();
    private int size = 0;

    public void addAtm(Atm atm) {
        atms.add(atm);
        ++size;
    }

    public Iterator<Atm> iterator() {
        return atms.iterator();
    }

    public int getAtmCount() {
        return size;
    }
}
