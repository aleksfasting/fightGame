package model.character;

import java.io.File;

import javax.imageio.ImageIO;

public class Character implements Creature, Playable {
    private double xPosition;
    private double yPosition;
    private double xSpeed;
    private double ySpeed;
    private int health;
    private boolean facingRight;
    private int speed;
    private String name;
    private String imgPath;
    private boolean jumping;
    private int height;
    private int imageIndex;
    private boolean stunned;
    private boolean punching;
    private boolean moving;
    private boolean falling;

    public Character(String name, String imgPath) {
        xPosition = 100;
        yPosition = 100;
        xSpeed = 0;
        ySpeed = 0;
        imageIndex = 0;
        health = 100;
        facingRight = true;
        speed = 6;
        jumping = false;
        this.name = name;
        this.imgPath = imgPath;
        stunned = false;
        moving = false;
        falling = false;

        try {
            height = ImageIO.read(new File(imgPath + "stand_r.png")).getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void die() {
        xPosition = 400;
        yPosition = 100;
        xSpeed = 0;
        ySpeed = 0;
        stunned = true;
        jumping = true;
        imageIndex = 0;
    }

    @Override
    public void jump() {
        ySpeed = -10;
        xPosition += xSpeed;
        if (jumping) {
            falling = true;
        }
        jumping = true;
    }

    public boolean isFalling() {
        return falling;
    }

    @Override
    public void quickAttack() {
        imageIndex = 24;
        speed = speed/2;
        punching = true;
    }

    @Override
    public void stun(int time) {
        stunned = true;
        ySpeed = 0;
        imageIndex = 0;
    }

    @Override
    public boolean isStunned() {
        return stunned;
    }

    @Override
    public void upAttack() {

    }

    @Override
    public void hardAttack() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSelectedIMG() {
        if (facingRight) {
            if (punching) {
                if (imageIndex > 40) {
                    return "qAttack_r3.png";
                } else if (imageIndex > 27) {
                    return "qAttack_r2.png";
                } else if (imageIndex > 25) {
                    return "qAttack_r1.png";
                }
                return "qAttack_r0.png";
            } else if (jumping) {
                return "jump_r.png";
            } else if (moving) {
                return "run_r" + (imageIndex / 6) + ".png";
            }
            return "stand_r.png";
        }
        if (punching) {
            if (imageIndex > 40) {
                return "qAttack_l3.png";
            } else if (imageIndex > 27) {
                return "qAttack_l2.png";
            } else if (imageIndex > 25) {
                return "qAttack_l1.png";
            }
            return "qAttack_l0.png";
        } else if (jumping) {
            return "jump_l.png";
        } else if (moving) {
            return "run_l" + (imageIndex / 6) + ".png";
        }
        return "stand_l.png";
    }

    @Override
    public boolean isJumping() {
        return jumping;
    }

    @Override
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
    public void stand() {
        moving = false;
    }

    @Override
    public void moveXPosition(float f) {
        xPosition += speed * f;
        facingRight = (f > 0);
        moving = true;
    }

    @Override
    public int getXPosition() {
        return (int)xPosition;
    }

    @Override
    public int getYPosition() {
        return (int)yPosition;
    }

    @Override
    public void fall(int width, int height) {
        imageIndex += 1;
        if (imageIndex == 48) {
            imageIndex = 0;
            speed = 6;
            stunned = false;
            punching = false;
        }
        ySpeed += 0.15;
        yPosition += ySpeed;
        if (yPosition >= height-this.height) {
            yPosition = height-this.height;
            ySpeed = 0;
            jumping = false;
            falling = false;
        }
        if (xPosition < -250 || xPosition > width + 250) {
            die();
        }
    }
}
