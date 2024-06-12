package model.field;

import java.util.ArrayList;

import controller.KeyboardController;
import model.character.Character;

public class Field {
    private int width;
    private int height;
    private ArrayList<Character> characters;

    public Field(int height, int width) {
        this.width = width;
        this.height = height;
        characters = new ArrayList<Character>();
    }

    public void addCharacter(String name, String imgPath) {
        characters.add(new Character(name, imgPath));
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void act(KeyboardController kbC) {
        kbC.pollController();
        for (Character c : characters) {
            c.fall(width, height);
            if (c.isStunned()) {
                c.stand();
                continue;
            }
            if (kbC.getQAttack()) {
                c.quickAttack();
                c.stun(1000);
                continue;
            }
            if (Math.abs(kbC.getX()) > 0.2) {
                c.moveXPosition(kbC.getX());
            } else if (!c.isJumping()) {
                c.stand();
            }
            if (kbC.getUp() && !c.isJumping()) {
                c.jump();
            }
        }
    }
}