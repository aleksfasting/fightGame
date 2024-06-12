package model.character;

import java.io.File;

import javax.imageio.ImageIO;

import model.field.Section;

public class Character implements Creature, Playable {
    private double xPosition;
    private double yPosition;
    private double xSpeed;
    private double ySpeed;
    private int health;
    private boolean facingRight;
    private int speed;
    private Section section;
    private String name;
    private String imgPath;
    private boolean jumping;
    private int height;
    private String selectedIMG;
    private int imageIndex;
    private boolean stunned;
    private boolean punching;

    public Character(Section s, String name, String imgPath) {
        section = s;
        xPosition = 100;
        yPosition = 100;
        xSpeed = 0;
        ySpeed = 0;
        imageIndex = 0;
        health = 100;
        facingRight = true;
        speed = 5;
        jumping = false;
        this.name = name;
        this.imgPath = imgPath;
        this.selectedIMG = "stand_r.png";
        stunned = false;

        try {
            height = ImageIO.read(new File(imgPath + "stand_r.png")).getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jump() {
       ySpeed = -10;
       xPosition += xSpeed;
       jumping = true;
       if (facingRight) {
           selectedIMG = "jump_r.png";
           return;
       }
       selectedIMG = "jump_l.png";
    }

    @Override
    public void quickAttack() {
        if (facingRight) {
            selectedIMG = "qAttack_r0.png";
            punching = true;
            return;
        }
        selectedIMG = "qAttack_l0.png";
        punching = true;
    }

    public void stun(int time) {
        stunned = true;
        ySpeed = 0;
        imageIndex = 0;
    }

    public boolean isStunned() {
        return stunned;
    }

    @Override
    public void upAttack() {

    }

    @Override
    public void hardAttack() {

    }

    public String getName() {
        return name;
    }

    public String getSelectedIMG() {
        return selectedIMG;
    }

    public boolean isJumping() {
        return jumping;
    }

    public String getImgPath() {
        return imgPath;
    }

    @Override
    public void specialAttack() {

    }

    @Override
    public void block() {

    }

    @Override
    public void dodge() {

        xPosition -= 10;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public boolean getFacingDirection() {
        return facingRight;
    }

    @Override
    public void moveLeft(float f) {
        xPosition += speed * f;
        if (!jumping) {
            selectedIMG = "run_l" + (int)(imageIndex/12) + ".png";
        }
        facingRight = false;
    }

    public void stand() {
        if (facingRight) {
            if (punching) {
                if (imageIndex > 27) {
                    selectedIMG = "qAttack_r1.png";
                }
                return;
            }
            selectedIMG = "stand_r.png";
            return;
        }
        if (punching) {
            if (imageIndex > 27) {
                selectedIMG = "qAttack_l1.png";
            }
            return;
        }
        selectedIMG = "stand_l.png";
    }

    @Override
    public void moveRight(float f) {
        xPosition += speed * f;
        if (!jumping) {
            selectedIMG = "run_r" + (int)(imageIndex/12) + ".png";
        }
        facingRight = true;
    }

    @Override
    public int getXPosition() {
        return (int)xPosition;
    }

    @Override
    public int getYPosition() {
        return (int)yPosition;
    }

    public void fall(int height) {
        if (jumping && !punching) {
            if (facingRight) {
                selectedIMG = "jump_r.png";
            } else {
                selectedIMG = "jump_l.png";
            }
        }
        imageIndex += 1;
        if (imageIndex == 48) {
            imageIndex = 0;
            stunned = false;
            punching = false;
        }
        ySpeed += 0.1;
        yPosition += ySpeed;
        if (yPosition >= height-this.height) {
            yPosition = height-this.height;
            ySpeed = 0;
            jumping = false;
        }
    }
}
