package materials;

import processing.core.PApplet;

public class Mass {
    private double mass, x, y, vx, vy, ax, ay, size;

    public Mass(double mass, double x, double y) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;
        ax = 0;
        ay = 0;
        size = 50 * Math.pow(mass / 100, 1 / 3.0);
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double newMass) {
        mass = newMass;
    }

    public void draw(PApplet p) {
        p.rect((float)x, (float)y, (float)size, (float)size);
    }

    public void act() {
        vx += ax;
        vy += ay;
        x += vx;
        y += vy;
    }

    public void setAX(double newAX) {
        ax = newAX;
    }

    public void setAY(double newAY) {
        ay = newAY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}