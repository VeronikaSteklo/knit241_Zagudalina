package org.knit.solutions.semestr1.lab3;

public class PlayerFabric {
    public Warrior createWarrior(String name) {
        return new Warrior(name, 100, 0, 0, 10, 20, 1);
    }

    public WarriorTank createWarriorTank(String name) {
        return new WarriorTank(name, 100, 0, 0, 10, 20, 1, 15);
    }

    public Mage createMage(String name) {
        return new Mage(name, 80, 0, 0, 30, 5, 20);
    }

    public Priest createPriest(String name) {
        return new Priest(name, 80, 0, 0, 20, 5, 20);
    }
}
