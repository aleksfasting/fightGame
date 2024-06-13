package model.field;

import java.util.ArrayList;

import controller.KeyboardController;
import model.character.Character;
import model.character.Damage;

public class Field {
    private int width;
    private int height;
    private ArrayList<Character> characters;
    private ArrayList<Damage> damages;

    public Field(int height, int width) {
        damages = new ArrayList<Damage>();
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

    public void checkDamages() {
        ArrayList<Damage> removals = new ArrayList<Damage>();
        for (Damage d : damages) {
            if (d.getState().equals("DEAD")) {
                removals.add(d);
            }
        }
        damages.removeAll(removals);
    }

    public void act(KeyboardController kbC) {
        kbC.pollController();
        checkDamages();
        for (Damage d : damages) {
            d.update();
            if (d.getState().equals("ACTIVE")) {
                System.out.println("HYYYAAAAA");
            }
        }
        for (Character c : characters) {
            damages.addAll(c.getDamages());
            c.fall(width, height);
            if (c.isStunned()) {
                c.stand();
            if (Math.abs(kbC.getX()) > 0.2) {
                c.moveXPosition(kbC.getX());
            }
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
            if (kbC.getUp() && !c.isFalling()) {
                c.jump();
            }
        }
    }
}