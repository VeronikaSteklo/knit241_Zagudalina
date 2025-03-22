package org.knit.solutions.semestr1.lab3;

public class Priest extends Player implements Healer {
    public Priest(String name, int maxHealth, double posX, double posY, int damage, int defence, int attackRadius) {
        super(name, maxHealth, posX, posY, damage, defence, attackRadius);
    }

    @Override
    public void heal(Player player) {
        if (this.isAnotherPosNear(player.getPosX(), player.getPosY())) {
            if (!player.isAlive()) {
                player.increaseHealth(-player.getHealth() + this.getDamage());
                System.out.println("Священник " + getName() + " возродил ранее мертвого игрока " + player.getName());
            } else {
                player.increaseHealth(this.getDamage());
                System.out.println("Священник " + getName() + " похилил " + this.getDamage() + " хп игроку " + player.getName());
            }
        } else {
            System.out.println("Священник " + getName() + " находится слишком далеко от игрока " + player.getName() + " чтобы похилить его");
        }
    }
}
