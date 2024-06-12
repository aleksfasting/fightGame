package model.character;

public interface Creature {
    int getHealth();
    void setHealth(int health);
    void takeDamage(int damage);
    boolean getFacingDirection();
    void moveLeft(float f);
    void moveRight(float f);
    int getXPosition();
    int getYPosition();
}
