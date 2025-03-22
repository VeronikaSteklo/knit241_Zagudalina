package org.knit.solutions.semestr1.lab3;

public class Mage extends Player implements SpellCaster {
    public Mage(String name, int maxHealth, double posX, double posY, int damage, int defence, int attackRadius) {
        super(name, maxHealth, posX, posY, damage, defence, attackRadius);
    }

    @Override
    public void castSpell(Player player) {
        if (!player.isAlive()) {
            System.out.println("Маг " + getName() + " пытается добить мертвого игрока " + player.getName());
        } else {
            if (this.isAnotherPosNear(player.getPosX(), player.getPosY())) {
                player.decreaseHealth(this.getDamage());
                System.out.println("Маг " + getName() + " нанес " + this.getDamage() + " урона игроку " + player.getName());
            } else {
                System.out.println("Маг " + getName() + " находится слишком далеко от игрока " + player.getName() + " чтобы нанести ему урон");
            }
        }
    }
}
