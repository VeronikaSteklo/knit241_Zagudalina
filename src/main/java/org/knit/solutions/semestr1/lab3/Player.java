package org.knit.solutions.semestr1.lab3;

public abstract class Player {
    private String name;
    private int health;
    private final int maxHealth;
    private double posX;
    private double posY;
    private final int damage;
    private final int defence;
    private final int attackRadius;

    public Player(
            String name,
            int maxHealth,
            double posX,
            double posY,
            int damage,
            int defence,
            int attackRadius
    ) {
        this.name = name;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.posX = posX;
        this.posY = posY;
        this.damage = damage;
        this.defence = defence;
        this.attackRadius = attackRadius;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return health > 0;
    }

    protected int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    protected void increaseHealth(int value) {
        health = Math.min((health + value), maxHealth);
    }

    protected void decreaseHealth(int value) {
        health = health + defence - value;
    }

    protected double getPosX() {
        return posX;
    }

    protected double getPosY() {
        return posY;
    }

    protected boolean isAnotherPosNear(double posXToCompare, double posYToCompare) {
        return (Math.abs(Math.hypot(posX, posY) - Math.hypot(posXToCompare, posYToCompare)) < attackRadius);
    }

    public void move(double newX, double newY) {
        this.posX = newX;
        this.posY = newY;
    }

    protected int getDamage() {
        return damage;
    }

    protected int getDefence() {
        return defence;
    }

    @Override
    public String toString() {
        return "Игрок " + name + " : " +
                health + " hp at " +
                "(" + posX + "," + posY + ") " +
                "with " + damage + " damage " +
                "and " + defence + " defence";
    }
}
