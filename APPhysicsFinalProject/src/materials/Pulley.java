package materials;

import processing.core.PApplet;

public class Pulley extends Mass {
    private double omega;
    private double alpha;
    private double radius;

    public Pulley(double mass, double x, double y) {
        super(mass, x, y);
        omega = 0;
        alpha = 0;
        radius = 150;
    }

    public void act() {
        super.act();
        omega += alpha;
    }

    public double getOmega() {
        return omega;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double newAlpha) {
        alpha = newAlpha;
    }

    public double getRadius() {
        return radius;
    }

    public void draw(PApplet p) {
        p.ellipse((float)getX(), (float)getY(), (float)radius * 2, (float)radius * 2);
    }
}