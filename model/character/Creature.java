package model.character;

public interface Creature {
    int getHealth();
    void setHealth(int health);
    void takeDamage(int damage);
    boolean getFacingDirection();
    void moveXPosition(float f);
    int getXPosition();
    int getYPosition();
    void fall(int width, int height);
    void stand();
    String getImgPath();
    String getSelectedIMG();
    String getName();
    void die();
}
