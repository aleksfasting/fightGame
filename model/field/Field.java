package model.field;

import java.util.ArrayList;

import controller.KeyboardController;
import model.character.Character;

public class Field {
    private int width;
    private int height;
    private ArrayList<Character> characters;
    private Section[][] sections;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        characters = new ArrayList<Character>();
    }

    public void addCharacter(String name, String imgPath) {
        characters.add(new Character(sections[0][0], name, imgPath));
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void act(KeyboardController kbC) {
        kbC.pollController();
        for (Character c : characters) {
            c.fall(height);
            if (c.isStunned()) {
                c.stand();
                continue;
            }
            if (kbC.getQAttack()) {
                c.quickAttack();
                c.stun(1000);
                continue;
            }
            if (kbC.getX() < -0.2) {
                c.moveLeft(kbC.getX());
            } else if (kbC.getX() > 0.2) {
                c.moveRight(kbC.getX());
            } else if (!c.isJumping()) {
                c.stand();
            }
            if (kbC.getUp() && !c.isJumping()) {
                c.jump();
            }
        }
    }

    public void createSections() {
        sections = new Section[(int)width/16][(int)height/16];
        for (int i = 0; i < width/16; i+=1) {
            for (int j = 0; j < height/16; j+=1) {
                sections[i][j] = new Section(i*16, j*16);
            }
        }

        sections[0][0].setNeighbor(4, sections[1][0]);
        sections[0][0].setNeighbor(6, sections[0][1]);
        sections[0][0].setNeighbor(7, sections[1][1]);
        sections[0][(int)height/16-1].setNeighbor(3, sections[0][(int)height/16-2]);
        sections[0][(int)height/16-1].setNeighbor(5, sections[1][(int)height/16-2]);
        sections[0][(int)height/16-1].setNeighbor(6, sections[1][(int)height/16-1]);
        sections[(int)width/16-1][0].setNeighbor(2, sections[(int)width/16-2][0]);
        sections[(int)width/16-1][0].setNeighbor(3, sections[(int)width/16-2][1]);
        sections[(int)width/16-1][0].setNeighbor(5, sections[(int)width/16-1][1]);
        sections[(int)width/16-1][(int)height/16-1].setNeighbor(0, sections[(int)width/16-2][(int)height/16-2]);
        sections[(int)width/16-1][(int)height/16-1].setNeighbor(1, sections[(int)width/16-1][(int)height/16-2]);
        sections[(int)width/16-1][(int)height/16-1].setNeighbor(3, sections[(int)width/16-2][(int)height/16-1]);

        for (int i = 1; i < width/16-1; i+=1) {
            sections[i][0].setNeighbor(3, sections[i-1][0]);
            sections[i][0].setNeighbor(4, sections[i+1][0]);
            sections[i][0].setNeighbor(5, sections[i-1][1]);
            sections[i][0].setNeighbor(6, sections[i][1]);
            sections[i][0].setNeighbor(7, sections[i+1][1]);

            for (int j = 1; j < height/16-1; j+=1) {
                sections[i][j].setNeighbor(0, sections[i-1][j-1]);
                sections[i][j].setNeighbor(1, sections[i][j-1]);
            }
            sections[i][(int)height/16-1].setNeighbor(0, sections[i-1][(int)height/16-2]);
            sections[i][(int)height/16-1].setNeighbor(1, sections[i][(int)height/16-2]);
            sections[i][(int)height/16-1].setNeighbor(2, sections[i+1][(int)height/16-2]);
            sections[i][(int)height/16-1].setNeighbor(3, sections[i-1][(int)height/16-1]);
            sections[i][(int)height/16-1].setNeighbor(4, sections[i+1][(int)height/16-1]);
        }

        for (int j = 1; j < height/16 - 1; j+=1) {
            sections[0][j].setNeighbor(1, sections[0][j-1]);
            sections[0][j].setNeighbor(2, sections[1][j-1]);
            sections[0][j].setNeighbor(4, sections[1][j]);
            sections[0][j].setNeighbor(6, sections[0][j+1]);
            sections[0][j].setNeighbor(7, sections[1][j+1]);
    
            sections[(int)width/16-1][j].setNeighbor(0, sections[(int)width/16-2][j-1]);
            sections[(int)width/16-1][j].setNeighbor(1, sections[(int)width/16-2][j]);
            sections[(int)width/16-1][j].setNeighbor(3, sections[(int)width/16-1][j-1]);
            sections[(int)width/16-1][j].setNeighbor(5, sections[(int)width/16-2][j+1]);
            sections[(int)width/16-1][j].setNeighbor(6, sections[(int)width/16-1][j+1]);
        }
    }
}