package ru.otus.department;

import ru.otus.atm.Atm;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Alexey on 23.05.2017.
 */
public class AtmDepartment {

    private AtmStorage atmStorage = new AtmStorage();

    public void addAtm(Atm atm) {
        atmStorage.addAtm(atm);
    }

    public int getAtmCount() {
        return atmStorage.getAtmCount();
    }

    public void printBalance() {
        Iterator<Atm> it = atmStorage.iterator();
        while (it.hasNext()) {
            Atm atm = it.next();
            System.out.println("id = " + atm.getId() + ". Balance = " + atm.getBalance());
        }
    }

    public Map<Integer, Integer> getBalance() {
        Map<Integer, Integer> idBalanceMap = new TreeMap<>();
        Iterator<Atm> it = atmStorage.iterator();
        while (it.hasNext()) {
            Atm atm = it.next();
            idBalanceMap.put(atm.getId(), atm.getBalance());
        }
        return idBalanceMap;
    }


    public Map<Integer, Integer> withdraw(int id, int request) {
        Iterator<Atm> it = atmStorage.iterator();
        while (it.hasNext()) {
            Atm atm = it.next();
            if (id == atm.getId()) {
                return atm.withdraw(request);
            }
        }
        return  null;
    }
}
