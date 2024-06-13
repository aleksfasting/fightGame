package model.character;

public class Damage {
    protected String caller;
    protected int damage;
    protected int xPos;
    protected int yPos;
    protected int radius;
    protected int delay;
    protected String state; // ALIVE, ACTIVE, DEAD

    public Damage(String caller, int damage, int xPos, int yPos, int radius, int delay) {
        this.caller = caller;
        this.damage = damage;
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.state = "ALIVE";
        this.delay = delay;
    }

    public void update() {
        if (delay > 0) {
            delay--;
        } else if (delay == 0) {
            state = "ACTIVE";
            delay = -1;
        } else {
            state = "DEAD";
        }
    }

    public String getState() {
        return state;
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
