package org.knit.solutions.semestr1.lab3;

public class WarriorTank extends Warrior {
    Integer additionalDefence;

    public WarriorTank(String name, int maxHealth, double posX, double posY, int damage, int defence, int attackRadius, Integer additionalDefence) {
        super(name, maxHealth, posX, posY, damage, defence, attackRadius, additionalDefence);
        this.additionalDefence = additionalDefence;
    }

    @Override
    public void decreaseHealth(int value) {
        int health = this.getHealth();
        health = health + getDefence() - value - additionalDefence;
        this.setHealth(health);
    }
}
