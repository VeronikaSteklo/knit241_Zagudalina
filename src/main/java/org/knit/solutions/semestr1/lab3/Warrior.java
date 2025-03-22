package org.knit.solutions.semestr1.lab3;

public class Warrior extends Player implements Attacker {
    public Warrior(String name, int maxHealth, double posX, double posY, int damage, int defence, int attackRadius) {
        super(name, maxHealth, posX, posY, damage, defence, attackRadius);
    }

    public Warrior(String name, int maxHealth, double posX, double posY, int damage, int defence, int attackRadius, Integer additionalDefence) {
        super(name, maxHealth, posX, posY, damage, defence, attackRadius);
    }

    @Override
    public void decreaseHealth(int value) {
        int health = this.getHealth();
        health = health + getDefence() - value;
        this.setHealth(health);
    }

    @Override
    public void attack(Player player) {
        if (!player.isAlive()) {
            System.out.println("Воин " + getName() + " пытается добить мертвого игрока " + player.getName());
        } else {
            if (this.isAnotherPosNear(player.getPosX(), player.getPosY())) {
                player.decreaseHealth(this.getDamage());
                System.out.println("Воин " + getName() + " нанес " + this.getDamage() + " урона игроку " + player.getName());
            } else {
                System.out.println("Воин " + getName() + " находится слишком далеко от игрока " + player.getName() + " чтобы нанести ему урон");
            }
        }
    }
}
