package materials;

import processing.core.PApplet;

public class Mass {
    private double mass, x, y, vx, vy, ax, ay, size;

    public Mass(double mass, double x, double y, double size) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;
        ax = 0;
        ay = 0;
        this.size = size; // * Math.pow(mass / 100, 1 / 3.0);
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double newMass) {
        mass = newMass;
    }

    public void draw(PApplet p, double theta) {
        p.pushStyle();
        p.pushMatrix();
        p.translate((float)x, (float)y);
        p.rotate((float)theta);
        p.rect((float)(-size / 2), (float)(-size / 2), (float)size, (float)size);
        p.popMatrix();
        p.fill(0);
        p.text(Math.round(mass) + " g", (float)x - 25, (float)y);
        p.popStyle();
    }

    public void act() {
        vx += ax;
        vy += ay;
        x += vx;
        y += vy;
    }

    public double getSize() {
        return size;
    }

    public void setAX(double newAX) {
        ax = newAX;
    }

    public void setAY(double newAY) {
        ay = newAY;
    }

    public void setVX(double newVX) {
        vx = newVX;
    }

    public void setVY(double newVY) {
        vy = newVY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }
}