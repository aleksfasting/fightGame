package model.character;

public class Damage {
    protected int damage;
    protected int xPos;
    protected int yPos;
    protected int radius;

    public Damage(int damage, int xPos, int yPos, int radius) {
        this.damage = damage;
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    public int getDamage() {
        return damage;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getRadius() {
        return radius;
    }
}
