package model.character;

public class Projectile extends Damage {
    private int xSpeed;
    private int ySpeed;
    private double distance;

    public Projectile(int damage, int xPos, int yPos, int radius, int xSpeed, int ySpeed, double distance) {
        super(damage, xPos, yPos, radius);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.distance = distance;
    }

    public void travel() {
        xPos += xSpeed;
        yPos += ySpeed;
        distance -= Math.sqrt(xSpeed*xSpeed + ySpeed*ySpeed);
    }

    public double getDistance() {
        return distance;
    }
}
