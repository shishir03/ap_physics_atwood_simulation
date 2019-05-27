package materials;

import processing.core.PApplet;
import processing.core.PImage;

public class Pulley extends Mass {
    private double omega;
    private double theta;
    private double alpha;
    private double radius;

    public Pulley(double mass, double x, double y) {
        super(mass, x, y, 75);
        omega = 0;
        alpha = 0;
        radius = 75;
        theta = 0;
    }

    public void act() {
        super.act();
        omega += alpha;
        theta += omega;
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

    public void setOmega(double newOmega) {
        omega = newOmega;
    }

    public double getRadius() {
        return radius;
    }

    public void draw(PApplet p) {
        p.pushStyle();
        p.pushMatrix();
        p.translate((float)getX(), (float)getY());
        p.rotate((float)theta);
        p.ellipse(0, 0, (float)radius*2, (float)radius*2);
        p.strokeWeight(5);
        p.point((float)(getRadius() * 0.65), 0);
        p.popMatrix();
        p.fill(0);
        p.text(Math.round(getMass()) + " g", (float)getX() - 25, (float)getY());
        p.popStyle();
    }
}