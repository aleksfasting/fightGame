package model.character;

public interface Playable {
    void jump();
    boolean isJumping();
    void quickAttack();
    void upAttack();
    void hardAttack();
    void specialAttack();
    void block();
    void dodge();
    void stun(int time);
    boolean isStunned();
}
